package com.acropolis.bfhl.service;

import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;

public interface IBfhlService {

    BfhlResponse process(BfhlRequest request);
}
