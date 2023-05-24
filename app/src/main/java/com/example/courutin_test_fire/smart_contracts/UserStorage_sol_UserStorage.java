package com.example.courutin_test_fire.smart_contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
    public static final String BINARY = "608060405234801561001057600080fd5b506101e1806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632909f6381461005157806342f6487a1461006d578063aa8c217c1461008b578063af640d0f146100a9575b600080fd5b61006b6004803603810190610066919061012e565b6100c7565b005b6100756100e1565b6040516100829190610190565b60405180910390f35b6100936100e7565b6040516100a09190610190565b60405180910390f35b6100b16100ed565b6040516100be9190610190565b60405180910390f35b826000819055508160018190555080600281905550505050565b60015481565b60025481565b60005481565b600080fd5b6000819050919050565b61010b816100f8565b811461011657600080fd5b50565b60008135905061012881610102565b92915050565b600080600060608486031215610147576101466100f3565b5b600061015586828701610119565b935050602061016686828701610119565b925050604061017786828701610119565b9150509250925092565b61018a816100f8565b82525050565b60006020820190506101a56000830184610181565b9291505056fea2646970667358221220fc294b0003d984b890e8017c03307a56c934bec44684ad64fb3f9988cc1167b264736f6c63430008130033";

    public static final String FUNC_AMOUNT = "amount";

    public static final String FUNC_ID = "id";

    public static final String FUNC_PAYMENT = "payment";

    public static final String FUNC_SETDATA = "setData";

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

    public RemoteFunctionCall<BigInteger> amount() {
        final Function function = new Function(FUNC_AMOUNT, 
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

    public RemoteFunctionCall<BigInteger> payment() {
        final Function function = new Function(FUNC_PAYMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setData(BigInteger _id, BigInteger _payment, BigInteger _amount) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint256(_payment), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
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
