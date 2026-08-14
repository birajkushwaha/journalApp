package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArugmentSourceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("ram").password("ram").build()),
                Arguments.of(User.builder().userName("biraj").password("biraj").build()),
                Arguments.of(User.builder().userName("rahul").password("rahul").build())
        );
    }
}
