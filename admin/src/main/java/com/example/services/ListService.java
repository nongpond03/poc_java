package com.example.services;

import com.example.models.*;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {
    public List<Agent> getAgent() {
        List<Agent> agents = new ArrayList<>();
        val firstAgent = new Agent("000", "FCN", "ฟันด์ คอนเน็กซ์", "FUND CONNEXT", true, null, null, null, "*");
        val secondAgent = new Agent("001", "STT", "บริษัท เซ็ทเทรด ดอท คอม จำกัด", "Settrade.com Co., Ltd.", true, null, null, null, "10.14.*,10.24.*,10.23.*,10.32.25.36,10.22.25.34");
        agents.add(firstAgent);
        agents.add(secondAgent);
       return agents;
    }
    public List<Bank> getBank() {
        List<Bank> banks = new ArrayList<>();
        val firstBank = new Bank("002", "BBL", "บีบีแอลเปลี่ยนชื่อ", "BANGKOK BANK", "", true, null, null, null);
        val secondBank = new Bank("004", "KBANK", "กสิกรไทย", "KASIKORN BANK", "", true, null, null, null);
        banks.add(firstBank);
        banks.add(secondBank);
        return banks;
    }

    public List<PartiId> getPartiId() {
        List<PartiId> partiIds = new ArrayList<>();
        val firstPartiId = new PartiId("000", "TCH", "ทีซีเฮช", "TCH", "yodchaik@set.or.th", true, false, null, null,null,null);
        val secondPartiId = new PartiId("001", "FWW", "ฟรีวิว", "Freewill", "yodchaik@set.or.th", false,true,"10.14.*", null, null, null);
        partiIds.add(firstPartiId);
        partiIds.add(secondPartiId);
        return partiIds;
    }

    public List<Status> getStatus() {
        List<Status> statuses= new ArrayList<>();
        val firstStatus = new Status("000", "Success");
        val secondStatus= new Status("001", "Fail");
        statuses.add(firstStatus);
        statuses.add(secondStatus);
        return statuses;
    }

    public List<BankContract> getBankContracts() {
        List<BankContract> bankContracts= new ArrayList<>();
        val firstContract = new BankContract("000", "099", "025", "2023-05-01", "2050-05-01","Y");
        bankContracts.add(firstContract);
        return bankContracts;
    }

    public List<AgentContract> getAgentContracts() {
        List<AgentContract> agentContracts= new ArrayList<>();
        val firstContract = new AgentContract("000", "099", "025");
        agentContracts.add(firstContract);
        return agentContracts;
    }

    public ErrorResponse getErrors() {
        val errorResponse = new ErrorResponse();
        List<ErrorResponse.Error> errors = new ArrayList<>();
        val firstError = new ErrorResponse.Error();
        firstError.setField("back_code");
        firstError.setMessage("must not be blank");
        errors.add(firstError);
        errorResponse.setMessage("Request body validation failed.");
        errorResponse.setErrors(errors);
        return errorResponse;
    }
}
