package com.example.courutin_test_fire.smart_contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class UserStorageFactory_sol_UserStorageFactory extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506105e6806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80630819da4f1461004657806333395ef214610050578063cb5c394414610080575b600080fd5b61004e61009e565b005b61006a60048036038101906100659190610246565b610131565b60405161007791906102b4565b60405180910390f35b610088610170565b604051610095919061038d565b60405180910390f35b60006040516100ac906101fe565b604051809103906000f0801580156100c8573d6000803e3d6000fd5b5090506000819080600181540180825580915050600190039060005260206000200160009091909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000818154811061014157600080fd5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b606060008054806020026020016040519081016040528092919081815260200182805480156101f457602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190600101908083116101aa575b5050505050905090565b610201806103b083390190565b600080fd5b6000819050919050565b61022381610210565b811461022e57600080fd5b50565b6000813590506102408161021a565b92915050565b60006020828403121561025c5761025b61020b565b5b600061026a84828501610231565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061029e82610273565b9050919050565b6102ae81610293565b82525050565b60006020820190506102c960008301846102a5565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b61030481610293565b82525050565b600061031683836102fb565b60208301905092915050565b6000602082019050919050565b600061033a826102cf565b61034481856102da565b935061034f836102eb565b8060005b83811015610380578151610367888261030a565b975061037283610322565b925050600181019050610353565b5085935050505092915050565b600060208201905081810360008301526103a7818461032f565b90509291505056fe608060405234801561001057600080fd5b506101e1806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632909f6381461005157806342f6487a1461006d578063aa8c217c1461008b578063af640d0f146100a9575b600080fd5b61006b6004803603810190610066919061012e565b6100c7565b005b6100756100e1565b6040516100829190610190565b60405180910390f35b6100936100e7565b6040516100a09190610190565b60405180910390f35b6100b16100ed565b6040516100be9190610190565b60405180910390f35b826000819055508160018190555080600281905550505050565b60015481565b60025481565b60005481565b600080fd5b6000819050919050565b61010b816100f8565b811461011657600080fd5b50565b60008135905061012881610102565b92915050565b600080600060608486031215610147576101466100f3565b5b600061015586828701610119565b935050602061016686828701610119565b925050604061017786828701610119565b9150509250925092565b61018a816100f8565b82525050565b60006020820190506101a56000830184610181565b9291505056fea2646970667358221220fc294b0003d984b890e8017c03307a56c934bec44684ad64fb3f9988cc1167b264736f6c63430008130033a26469706673582212201d1153b8a0f0c7b38481d4c550fb5cf6f77574c3e5aecae62f1db24fa345584864736f6c63430008130033";

    public static final String FUNC_CREATEUSERSTORAGE = "createUserStorage";

    public static final String FUNC_GETUSERSTORAGECONTRACTS = "getUserStorageContracts";

    public static final String FUNC_USERSTORAGECONTRACTS = "userStorageContracts";

    @Deprecated
    protected UserStorageFactory_sol_UserStorageFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserStorageFactory_sol_UserStorageFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserStorageFactory_sol_UserStorageFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserStorageFactory_sol_UserStorageFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createUserStorage() {
        final Function function = new Function(
                FUNC_CREATEUSERSTORAGE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getUserStorageContracts() {
        final Function function = new Function(FUNC_GETUSERSTORAGECONTRACTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> userStorageContracts(BigInteger param0) {
        final Function function = new Function(FUNC_USERSTORAGECONTRACTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static UserStorageFactory_sol_UserStorageFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserStorageFactory_sol_UserStorageFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserStorageFactory_sol_UserStorageFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserStorageFactory_sol_UserStorageFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserStorageFactory_sol_UserStorageFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserStorageFactory_sol_UserStorageFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserStorageFactory_sol_UserStorageFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserStorageFactory_sol_UserStorageFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserStorageFactory_sol_UserStorageFactory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserStorageFactory_sol_UserStorageFactory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserStorageFactory_sol_UserStorageFactory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserStorageFactory_sol_UserStorageFactory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<UserStorageFactory_sol_UserStorageFactory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(UserStorageFactory_sol_UserStorageFactory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<UserStorageFactory_sol_UserStorageFactory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(UserStorageFactory_sol_UserStorageFactory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
