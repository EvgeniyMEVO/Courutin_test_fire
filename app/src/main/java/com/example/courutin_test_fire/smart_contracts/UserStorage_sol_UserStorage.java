package com.example.courutin_test_fire.smart_contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class UserStorage_sol_UserStorage extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506104f7806100206000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80630e904e83146100675780631883c7cc1461008357806328bdcde8146100a1578063af640d0f146100d4578063b1cd06e6146100f2578063ecefb10a14610125575b600080fd5b610081600480360381019061007c919061039b565b610141565b005b61008b6101db565b60405161009891906103ea565b60405180910390f35b6100bb60048036038101906100b69190610405565b6101e8565b6040516100cb949392919061044d565b60405180910390f35b6100dc610235565b6040516100e991906103ea565b60405180910390f35b61010c60048036038101906101079190610405565b61023b565b60405161011c949392919061044d565b60405180910390f35b61013f600480360381019061013a919061039b565b6102f5565b005b60006040518060800160405280848152602001838152602001600015158152602001600081525090506001819080600181540180825580915050600190039060005260206000209060040201600090919091909150600082015181600001556020820151816001015560408201518160020160006101000a81548160ff021916908315150217905550606082015181600301555050505050565b6000600180549050905090565b600181815481106101f857600080fd5b90600052602060002090600402016000915090508060000154908060010154908060020160009054906101000a900460ff16908060030154905084565b60005481565b6000806000806001858154811061025557610254610492565b5b9060005260206000209060040201600001546001868154811061027b5761027a610492565b5b906000526020600020906004020160010154600187815481106102a1576102a0610492565b5b906000526020600020906004020160020160009054906101000a900460ff16600188815481106102d4576102d3610492565b5b90600052602060002090600402016003015493509350935093509193509193565b806001838154811061030a57610309610492565b5b906000526020600020906004020160030181905550600180838154811061033457610333610492565b5b906000526020600020906004020160020160006101000a81548160ff0219169083151502179055505050565b600080fd5b6000819050919050565b61037881610365565b811461038357600080fd5b50565b6000813590506103958161036f565b92915050565b600080604083850312156103b2576103b1610360565b5b60006103c085828601610386565b92505060206103d185828601610386565b9150509250929050565b6103e481610365565b82525050565b60006020820190506103ff60008301846103db565b92915050565b60006020828403121561041b5761041a610360565b5b600061042984828501610386565b91505092915050565b60008115159050919050565b61044781610432565b82525050565b600060808201905061046260008301876103db565b61046f60208301866103db565b61047c604083018561043e565b61048960608301846103db565b95945050505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fdfea2646970667358221220bbf9b2619cd60ff74968d3be320d56b74415bb3dc9415569408358ac8af3591364736f6c63430008130033";

    public static final String FUNC_BILLS = "bills";

    public static final String FUNC_GETBILL = "getBill";

    public static final String FUNC_GETBILLCOUNT = "getBillCount";

    public static final String FUNC_ID = "id";

    public static final String FUNC_SETINITIALBILL = "setInitialBill";

    public static final String FUNC_UPDATEBILL = "updateBill";

    @Deprecated
    protected UserStorage_sol_UserStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserStorage_sol_UserStorage(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserStorage_sol_UserStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserStorage_sol_UserStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>> bills(BigInteger param0) {
        final Function function = new Function(FUNC_BILLS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, Boolean, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>> getBill(BigInteger _index) {
        final Function function = new Function(FUNC_GETBILL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, Boolean, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, Boolean, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getBillCount() {
        final Function function = new Function(FUNC_GETBILLCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> id() {
        final Function function = new Function(FUNC_ID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setInitialBill(BigInteger _period, BigInteger _debt) {
        final Function function = new Function(
                FUNC_SETINITIALBILL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_period), 
                new org.web3j.abi.datatypes.generated.Uint256(_debt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateBill(BigInteger _index, BigInteger _paymentDate) {
        final Function function = new Function(
                FUNC_UPDATEBILL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_index), 
                new org.web3j.abi.datatypes.generated.Uint256(_paymentDate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static UserStorage_sol_UserStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserStorage_sol_UserStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserStorage_sol_UserStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserStorage_sol_UserStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserStorage_sol_UserStorage load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserStorage_sol_UserStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserStorage_sol_UserStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserStorage_sol_UserStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserStorage_sol_UserStorage> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserStorage_sol_UserStorage.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserStorage_sol_UserStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserStorage_sol_UserStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserStorage_sol_UserStorage> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserStorage_sol_UserStorage.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserStorage_sol_UserStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserStorage_sol_UserStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
