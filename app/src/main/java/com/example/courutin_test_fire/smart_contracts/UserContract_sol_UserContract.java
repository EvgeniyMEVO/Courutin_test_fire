package com.example.courutin_test_fire.smart_contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
public class UserContract_sol_UserContract extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162000dab38038062000dab83398181016040528101906200003791906200023d565b846000800190816200004a9190620005ab565b5083600060010190816200005f9190620005ab565b508260006002019081620000749190620005ab565b508160006003019081620000899190620005ab565b5080600060040190816200009e9190620005ab565b50505050505062000692565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6200011382620000c8565b810181811067ffffffffffffffff82111715620001355762000134620000d9565b5b80604052505050565b60006200014a620000aa565b905062000158828262000108565b919050565b600067ffffffffffffffff8211156200017b576200017a620000d9565b5b6200018682620000c8565b9050602081019050919050565b60005b83811015620001b357808201518184015260208101905062000196565b60008484015250505050565b6000620001d6620001d0846200015d565b6200013e565b905082815260208101848484011115620001f557620001f4620000c3565b5b6200020284828562000193565b509392505050565b600082601f830112620002225762000221620000be565b5b815162000234848260208601620001bf565b91505092915050565b600080600080600060a086880312156200025c576200025b620000b4565b5b600086015167ffffffffffffffff8111156200027d576200027c620000b9565b5b6200028b888289016200020a565b955050602086015167ffffffffffffffff811115620002af57620002ae620000b9565b5b620002bd888289016200020a565b945050604086015167ffffffffffffffff811115620002e157620002e0620000b9565b5b620002ef888289016200020a565b935050606086015167ffffffffffffffff811115620003135762000312620000b9565b5b62000321888289016200020a565b925050608086015167ffffffffffffffff811115620003455762000344620000b9565b5b62000353888289016200020a565b9150509295509295909350565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620003b357607f821691505b602082108103620003c957620003c86200036b565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302620004337fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82620003f4565b6200043f8683620003f4565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b60006200048c62000486620004808462000457565b62000461565b62000457565b9050919050565b6000819050919050565b620004a8836200046b565b620004c0620004b78262000493565b84845462000401565b825550505050565b600090565b620004d7620004c8565b620004e48184846200049d565b505050565b5b818110156200050c5762000500600082620004cd565b600181019050620004ea565b5050565b601f8211156200055b576200052581620003cf565b6200053084620003e4565b8101602085101562000540578190505b620005586200054f85620003e4565b830182620004e9565b50505b505050565b600082821c905092915050565b6000620005806000198460080262000560565b1980831691505092915050565b60006200059b83836200056d565b9150826002028217905092915050565b620005b68262000360565b67ffffffffffffffff811115620005d257620005d1620000d9565b5b620005de82546200039a565b620005eb82828562000510565b600060209050601f8311600181146200062357600084156200060e578287015190505b6200061a85826200058d565b8655506200068a565b601f1984166200063386620003cf565b60005b828110156200065d5784890151825560018201915060208501945060208101905062000636565b868310156200067d578489015162000679601f8916826200056d565b8355505b6001600288020188555050505b505050505050565b61070980620006a26000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806316fc32971461005c578063450496f4146100785780634f8632ba146100a8578063cea754eb146100ca578063e2f78c33146100e6575b600080fd5b610076600480360381019061007191906104a0565b610116565b005b610092600480360381019061008d91906104a0565b610145565b60405161009f91906104dc565b60405180910390f35b6100b061015d565b6040516100c1959493929190610587565b60405180910390f35b6100e460048036038101906100df91906105fd565b610429565b005b61010060048036038101906100fb91906104a0565b610445565b60405161010d9190610658565b60405180910390f35b60016006600083815260200190815260200160002060006101000a81548160ff02191690831515021790555050565b60056020528060005260406000206000915090505481565b600080600001805461016e906106a2565b80601f016020809104026020016040519081016040528092919081815260200182805461019a906106a2565b80156101e75780601f106101bc576101008083540402835291602001916101e7565b820191906000526020600020905b8154815290600101906020018083116101ca57829003601f168201915b5050505050908060010180546101fc906106a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610228906106a2565b80156102755780601f1061024a57610100808354040283529160200191610275565b820191906000526020600020905b81548152906001019060200180831161025857829003601f168201915b50505050509080600201805461028a906106a2565b80601f01602080910402602001604051908101604052809291908181526020018280546102b6906106a2565b80156103035780601f106102d857610100808354040283529160200191610303565b820191906000526020600020905b8154815290600101906020018083116102e657829003601f168201915b505050505090806003018054610318906106a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610344906106a2565b80156103915780601f1061036657610100808354040283529160200191610391565b820191906000526020600020905b81548152906001019060200180831161037457829003601f168201915b5050505050908060040180546103a6906106a2565b80601f01602080910402602001604051908101604052809291908181526020018280546103d2906106a2565b801561041f5780601f106103f45761010080835404028352916020019161041f565b820191906000526020600020905b81548152906001019060200180831161040257829003601f168201915b5050505050905085565b8060056000848152602001908152602001600020819055505050565b60066020528060005260406000206000915054906101000a900460ff1681565b600080fd5b6000819050919050565b61047d8161046a565b811461048857600080fd5b50565b60008135905061049a81610474565b92915050565b6000602082840312156104b6576104b5610465565b5b60006104c48482850161048b565b91505092915050565b6104d68161046a565b82525050565b60006020820190506104f160008301846104cd565b92915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610531578082015181840152602081019050610516565b60008484015250505050565b6000601f19601f8301169050919050565b6000610559826104f7565b6105638185610502565b9350610573818560208601610513565b61057c8161053d565b840191505092915050565b600060a08201905081810360008301526105a1818861054e565b905081810360208301526105b5818761054e565b905081810360408301526105c9818661054e565b905081810360608301526105dd818561054e565b905081810360808301526105f1818461054e565b90509695505050505050565b6000806040838503121561061457610613610465565b5b60006106228582860161048b565b92505060206106338582860161048b565b9150509250929050565b60008115159050919050565b6106528161063d565b82525050565b600060208201905061066d6000830184610649565b92915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806106ba57607f821691505b6020821081036106cd576106cc610673565b5b5091905056fea2646970667358221220f916b73d49d29dc4a04ceb8af203f448b32880bb658590e6530b372c1aa7f46464736f6c63430008130033";

    public static final String FUNC_DEBTPAID = "debtPaid";

    public static final String FUNC_DEBTPERMONTH = "debtPerMonth";

    public static final String FUNC_PAYDEBT = "payDebt";

    public static final String FUNC_SETDEBT = "setDebt";

    public static final String FUNC_USER = "user";

    @Deprecated
    protected UserContract_sol_UserContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UserContract_sol_UserContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected UserContract_sol_UserContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected UserContract_sol_UserContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> debtPaid(BigInteger param0) {
        final Function function = new Function(FUNC_DEBTPAID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> debtPerMonth(BigInteger param0) {
        final Function function = new Function(FUNC_DEBTPERMONTH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> payDebt(BigInteger _month) {
        final Function function = new Function(
                FUNC_PAYDEBT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_month)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDebt(BigInteger _month, BigInteger _debt) {
        final Function function = new Function(
                FUNC_SETDEBT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_month), 
                new org.web3j.abi.datatypes.generated.Uint256(_debt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<String, String, String, String, String>> user() {
        final Function function = new Function(FUNC_USER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, String, String, String>>(function,
                new Callable<Tuple5<String, String, String, String, String>>() {
                    @Override
                    public Tuple5<String, String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    @Deprecated
    public static UserContract_sol_UserContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserContract_sol_UserContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static UserContract_sol_UserContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserContract_sol_UserContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static UserContract_sol_UserContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserContract_sol_UserContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserContract_sol_UserContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new UserContract_sol_UserContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<UserContract_sol_UserContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _firstName, String _lastName, String _firebaseId, String _phoneNumber, String _userAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstName), 
                new org.web3j.abi.datatypes.Utf8String(_lastName), 
                new org.web3j.abi.datatypes.Utf8String(_firebaseId), 
                new org.web3j.abi.datatypes.Utf8String(_phoneNumber), 
                new org.web3j.abi.datatypes.Utf8String(_userAddress)));
        return deployRemoteCall(UserContract_sol_UserContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<UserContract_sol_UserContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _firstName, String _lastName, String _firebaseId, String _phoneNumber, String _userAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstName), 
                new org.web3j.abi.datatypes.Utf8String(_lastName), 
                new org.web3j.abi.datatypes.Utf8String(_firebaseId), 
                new org.web3j.abi.datatypes.Utf8String(_phoneNumber), 
                new org.web3j.abi.datatypes.Utf8String(_userAddress)));
        return deployRemoteCall(UserContract_sol_UserContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<UserContract_sol_UserContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _firstName, String _lastName, String _firebaseId, String _phoneNumber, String _userAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstName), 
                new org.web3j.abi.datatypes.Utf8String(_lastName), 
                new org.web3j.abi.datatypes.Utf8String(_firebaseId), 
                new org.web3j.abi.datatypes.Utf8String(_phoneNumber), 
                new org.web3j.abi.datatypes.Utf8String(_userAddress)));
        return deployRemoteCall(UserContract_sol_UserContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<UserContract_sol_UserContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _firstName, String _lastName, String _firebaseId, String _phoneNumber, String _userAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_firstName), 
                new org.web3j.abi.datatypes.Utf8String(_lastName), 
                new org.web3j.abi.datatypes.Utf8String(_firebaseId), 
                new org.web3j.abi.datatypes.Utf8String(_phoneNumber), 
                new org.web3j.abi.datatypes.Utf8String(_userAddress)));
        return deployRemoteCall(UserContract_sol_UserContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
