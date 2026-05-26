package com.acropolis.bfhl.service;

import com.acropolis.bfhl.config.BfhlUserProperties;
import com.acropolis.bfhl.dto.BfhlRequest;
import com.acropolis.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements IBfhlService {

    private final BfhlUserProperties userProperties;

    public BfhlServiceImpl(BfhlUserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        StringBuilder alphaConcat = new StringBuilder();
        long sum = 0;

        for (String item : data) {
            if (isInteger(item)) {
                long value = Long.parseLong(item);
                sum += value;
                if (value % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());
                alphaConcat.append(item);
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(alphaConcat.toString());

        return new BfhlResponse(
                true,
                userProperties.getUserId(),
                userProperties.getEmail(),
                userProperties.getRollNumber(),
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                String.valueOf(sum),
                concatString);
    }

    static boolean isInteger(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean isAlphabetic(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static String buildConcatString(String alphabeticConcat) {
        if (alphabeticConcat.isEmpty()) {
            return "";
        }
        String reversed = new StringBuilder(alphabeticConcat).reverse().toString();
        return applyAlternatingCase(reversed);
    }

    static String applyAlternatingCase(String input) {
        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        return result.toString();
    }
}
