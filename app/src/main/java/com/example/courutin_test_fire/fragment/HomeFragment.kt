package com.example.courutin_test_fire.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.courutin_test_fire.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.yoomoney.sdk.kassa.payments.Checkout.createTokenizationResult
import ru.yoomoney.sdk.kassa.payments.Checkout.createTokenizeIntent
import ru.yoomoney.sdk.kassa.payments.checkoutParameters.*
import java.math.BigDecimal
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var debtDouble by Delegates.notNull<Double>()
private var lastIndex by Delegates.notNull<Int>()
private lateinit var amount_info : TextView
private var all_info = "Оплата от: "
/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val bt_pay = view.findViewById<ImageView>(R.id.bt_pay)
        val bt_send = view.findViewById<ImageView>(R.id.bt_send)
        val ed_full_address = view.findViewById<TextView>(R.id.full_address)

        amount_info = view.findViewById(R.id.amount_info)


        updatePaid()

        bt_pay.setOnClickListener {
            initPayment()
        }
        bt_send.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SendInfoJKHBlockchain())
                .addToBackStack(null)
                .commit()
        }
        var city: String? = null
        var street: String? = null
        var house: String? = null
        var index: String? = null
        var name: String? = null
        var firstname: String? = null


        CoroutineScope(Dispatchers.IO).launch {
            var full_adress = "Адрес: "
            city = DatabaseManager.getAnyInfo("city").getOrThrow()
            street = DatabaseManager.getAnyInfo("street").getOrThrow()
            house = DatabaseManager.getAnyInfo("house").getOrThrow()
            index = DatabaseManager.getAnyInfo("index").getOrThrow()
            name = DatabaseManager.getAnyInfo("name").getOrThrow()
            firstname = DatabaseManager.getAnyInfo("firstname").getOrThrow()
            withContext(Dispatchers.Main) {
                city?.let { full_adress += city }
                street?.let { full_adress += "," + street }
                house?.let { full_adress += "," + house}
                index?.let { full_adress += "," + index }

                ed_full_address?.setText(full_adress)

            }
            firstname?.let { all_info += firstname + " " }
            name?.let { all_info += "$name\n$full_adress" }
        }
        // Inflate the layout for this fragment
        return view
    }

    fun updatePaid(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val blockchainManager = BlockchainManager.getInstance()
                var amountColor = 0
                lastIndex = blockchainManager.getLastIndex().toInt()
                var text = ""
                if (lastIndex != -1){
                    val (period, debt, isPaid) = blockchainManager.getLastBill()
                    if (isPaid){
                        debtDouble = 1.0
                        text = "0.0 ₽"
                        amountColor = R.color.paid
                    }
                    else{
                        debtDouble = debt.toDouble()
                        text = "$debtDouble ₽"
                        amountColor = R.color.unpaid
                    }
                }
                else{
                    // возможность опалаты не откроется если будет 0.0 в связи с этим 1.0
                    debtDouble = 1.0
                    text = "0.0 ₽"
                    amountColor = R.color.paid

                }
                withContext(Dispatchers.Main) {
                    // Здесь вы можете обновить пользовательский интерфейс с информацией о последнем счете
                    // Например:
                    amount_info.text = text
                    amount_info.setTextColor(resources.getColor(amountColor))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Здесь вы можете обработать ошибки
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_TOKENIZE) {
            when (resultCode) {
                Activity.RESULT_OK -> showToken(data)
                Activity.RESULT_CANCELED -> showError()
            }
        }
    }

    private fun showToken(data: Intent?) {
        if (data != null) {
            val token = createTokenizationResult(data).paymentToken
            val currentDate = Date()
            val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
            val dateString = dateFormat.format(currentDate)
            CoroutineScope(Dispatchers.IO).launch{
                BlockchainManager.getInstance().updateBill(
                    BigInteger.valueOf(lastIndex.toLong()),
                    BigInteger.valueOf(dateString.toLong()))
                updatePaid()
            }
            Toast.makeText(
                requireActivity(),
                String.format(Locale.getDefault(), getString(R.string.tokenization_success), token),
                Toast.LENGTH_LONG
            ).show()

        } else {
            showError()
        }
    }

    private fun showError() {
        Toast.makeText(requireActivity(), R.string.tokenization_canceled, Toast.LENGTH_SHORT).show()
    }



    private fun initPayment() {
        val paymentMethodTypes = setOf(
            PaymentMethodType.GOOGLE_PAY,
            PaymentMethodType.BANK_CARD,
            PaymentMethodType.SBERBANK,
            PaymentMethodType.YOO_MONEY
        )
        val paymentParameters = PaymentParameters(
            amount = Amount(BigDecimal.valueOf(debtDouble), Currency.getInstance("RUB")),
            title = getString(R.string.main_product_name),
            subtitle = all_info,
            clientApplicationKey = BuildConfig.MERCHANT_TOKEN,
            shopId = BuildConfig.SHOP_ID,
            savePaymentMethod = SavePaymentMethod.OFF,
            paymentMethodTypes = paymentMethodTypes,
            gatewayId = BuildConfig.GATEWAY_ID,
            customReturnUrl = getString(R.string.test_redirect_url),
            userPhoneNumber = getString(R.string.test_phone_number),
            googlePayParameters = GooglePayParameters(),
            authCenterClientId = BuildConfig.CLIENT_ID,
            customerId = AuthManager.getCurrentUserId()
        )

        val intent = createTokenizeIntent(
            requireActivity(),
            paymentParameters,
            TestParameters(showLogs = true))
        startActivityForResult(intent, REQUEST_CODE_TOKENIZE)
    }

    companion object {
        private const val REQUEST_CODE_TOKENIZE = 1
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}