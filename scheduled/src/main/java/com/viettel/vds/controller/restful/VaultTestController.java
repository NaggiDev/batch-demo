package com.viettel.vds.controller.restful;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;
import org.springframework.vault.support.Signature;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.viettel.vds.arch.controller.restful.BaseController;
import vn.com.viettel.vds.arch.factory.response.GeneralResponse;
import vn.com.viettel.vds.arch.factory.response.ResponseFactory;
import com.viettel.vds.constant.ResponseStatusCodeEnumClient;

import java.util.Map;

@RestController
@RequestMapping("/user-management/v1/api")
@Tag(name = "testVault", description = "testVault")
@Slf4j
public class VaultTestController  extends BaseController {
    @Autowired
    ResponseFactory responseFactory;

    @Autowired(required = false)
    VaultOperations vaultOps;

    @GetMapping(value = "/vaultGetKV")
    public ResponseEntity<GeneralResponse<String>> getKV(String key) {
        try {
            VaultResponse vaultResponse = vaultOps.opsForKeyValue("kv", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("dt-secret");
            if (vaultResponse != null) {
                Map<String, Object> map = vaultResponse.getData();
                if (map != null) {
                    return responseFactory.success(map.get(key).toString());

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseFactory.fail("Không tìm thấy", ResponseStatusCodeEnumClient.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/vaultEnc")
    public ResponseEntity<GeneralResponse<Ciphertext>> getEnc(String data) {
        try {
            Ciphertext vaultResponse = vaultOps.opsForTransit().encrypt("dt-transit", Plaintext.of(data));
            return responseFactory.success(vaultResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseFactory.fail( ResponseStatusCodeEnumClient.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/vaultDec")
    public ResponseEntity<GeneralResponse<String>> getDec(String data) {
        try {
            Plaintext vaultResponse = vaultOps.opsForTransit().decrypt("dt-transit", Ciphertext.of(data));
            return responseFactory.success(new String(vaultResponse.getPlaintext()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseFactory.fail( ResponseStatusCodeEnumClient.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/vaultSig")
    public ResponseEntity<GeneralResponse<Signature>> getSig(String data) {
        try {
            Signature vaultResponse = vaultOps.opsForTransit().sign("dt-transit-ecdsa-p256", Plaintext.of(data));
            return responseFactory.success(vaultResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseFactory.fail( ResponseStatusCodeEnumClient.DATA_NOT_FOUND);
    }

    @GetMapping(value = "/vaultVerify")
    public ResponseEntity<GeneralResponse<Boolean>> getVerify(String data, String sigData) {
        try {
            return responseFactory.success(vaultOps.opsForTransit().verify("dt-transit-ecdsa-p256", Plaintext.of(data), Signature.of(sigData)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return responseFactory.fail( ResponseStatusCodeEnumClient.DATA_NOT_FOUND);
    }
}
