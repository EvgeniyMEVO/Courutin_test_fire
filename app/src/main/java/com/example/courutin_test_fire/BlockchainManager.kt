package com.example.courutin_test_fire

import com.example.courutin_test_fire.smart_contracts.UserStorageFactory_sol_UserStorageFactory
import com.example.courutin_test_fire.smart_contracts.UserStorage_sol_UserStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigInteger
class BlockchainManager private constructor() {

    private var web3j: Web3j? = null
    private var contract: UserStorageFactory_sol_UserStorageFactory? = null
    private var personal_contract: UserStorage_sol_UserStorage? = null

    companion object {
        //0x15356842F66290ecAaA447364D18DA2491629264
        const val CONTRACT_ADDRESS = "0x851c8E8E0bcAC4277ff62341f9b7993B1E727c98"
        //const val PERSONAL_CONTRACT_ADDRESS = "0x215bd3167d3c47e140988b2159dfd7d53bffc01c"
        const val GAS_PRICE = 200
        const val GAS_LIMIT = 4500000

        @Volatile
        private var instance: BlockchainManager? = null

        fun getInstance(): BlockchainManager {
            return instance ?: synchronized(this) {
                instance ?: BlockchainManager().also { instance = it }
            }
        }
    }




    suspend fun setFabricaContractAddress() {
        withContext(Dispatchers.IO) {
            if (web3j == null) {
                withContext(Dispatchers.IO) {
                    val ganacheUrl = "http://10.0.2.2:7545"
                    web3j = Web3j.build(HttpService(ganacheUrl))
                }
            }
            val credentials = Credentials.create("0xa6209caf05e2846766970b74de2b38f67a587419fde5a45c7f059e7ca870d8a6")
            val transactionManager = RawTransactionManager(web3j, credentials)
            val gasPrice = getCurrentGasPrice()
            val gasProvider = StaticGasProvider(gasPrice, GAS_LIMIT.toBigInteger())

            contract = UserStorageFactory_sol_UserStorageFactory.load(
                CONTRACT_ADDRESS,
                web3j,
                transactionManager,
                gasProvider
            )
        }
    }

    suspend fun setPersonalContractAddress(address: String) {
        withContext(Dispatchers.IO) {
            if (web3j == null) {
                withContext(Dispatchers.IO) {
                    // localhost
                    val ganacheUrl = "http://10.0.2.2:7545"
                    web3j = Web3j.build(HttpService(ganacheUrl))
                }
            }
            val credentials = Credentials.create("0xa6209caf05e2846766970b74de2b38f67a587419fde5a45c7f059e7ca870d8a6")
            val transactionManager = RawTransactionManager(web3j, credentials)
            val gasPrice = getCurrentGasPrice()
            val gasProvider = StaticGasProvider(gasPrice, GAS_LIMIT.toBigInteger())

            personal_contract = UserStorage_sol_UserStorage.load(
                address,
                web3j,
                transactionManager,
                gasProvider
            )
        }
    }


    // Тут уже работаем с персональным контрактом, передаем данные
    suspend fun setInitialBill(period: BigInteger, debt: BigInteger) {
        setPersonalContractAddress(DatabaseManager.getUserContractAddress().getOrThrow().toString())
        personal_contract!!.setInitialBill(period, debt).send()
    }

    suspend fun updateBill(index: BigInteger, period: BigInteger) {
        personal_contract!!.updateBill(index, period).send()
    }

    suspend fun getLastIndex(): BigInteger {
        setPersonalContractAddress(DatabaseManager.getUserContractAddress().getOrThrow().toString())
        val count = personal_contract!!.getBillCount().send()
        val lastIndex = count - BigInteger.ONE
        return lastIndex
    }

    suspend fun getLastBill(): Triple<BigInteger, BigInteger, Boolean> {
        setPersonalContractAddress(DatabaseManager.getUserContractAddress().getOrThrow().toString())
        val count = personal_contract!!.getBillCount().send()
        val lastIndex = count - BigInteger.ONE
        if (lastIndex >= BigInteger.ZERO) {
            val bill = personal_contract!!.getBill(lastIndex).send()
            return Triple(bill.component1(), bill.component2(), bill.component3())
        }
        throw IllegalStateException("No bills available.")
    }


    suspend fun createUserStorage() {
        val txReceipt = contract!!.createUserStorage().send()
    }

    suspend fun getUserStorageContracts(): List<String> {
        val contracts = contract!!.getUserStorageContracts().send()
        val contractAddresses = mutableListOf<String>()
        for (contractAddress in contracts) {
            contractAddresses.add(contractAddress.toString())
        }
        return contractAddresses
    }

    suspend fun getLastUserStorageContract(): String? {
        val contracts = getUserStorageContracts()
        return contracts.lastOrNull()
    }

    //Динамически меняем стоимость газа в зависимости от состояния сети
    private suspend fun getCurrentGasPrice(): BigInteger {
        return web3j!!.ethGasPrice().sendAsync().get().gasPrice
    }


}