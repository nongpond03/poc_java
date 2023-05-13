package com.example.demo.person;

import java.util.List;

public record PersonRequest(List<IdentityCard> identityCards, List<Passport> passports) {
}
