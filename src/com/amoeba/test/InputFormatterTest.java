package com.amoeba.test;

import com.amoeba.game.MenuOption;
import com.amoeba.formatter.InputFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class InputFormatterTest {

    private InputFormatter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new InputFormatter();
    }

    @Test
    public void testInterpretMenuInputForWorkingCases() {
        //GIVEN
        String first = "1";
        String second = "2";
        String third = "3";
        //WHEN
        MenuOption actual1 = underTest.interpretMenuInput(first).get();
        MenuOption actual2 = underTest.interpretMenuInput(second).get();
        MenuOption actual3 = underTest.interpretMenuInput(third).get();
        //THEN
        assertEquals(MenuOption.NEW_GAME, actual1);
        assertEquals(MenuOption.LOAD_GAME, actual2);
        assertEquals(MenuOption.EXIT, actual3);
    }

    @Test
    public void testInterpretMenuInputShouldGiveEmptyResultWhenGettingWrongInputs() {
        //GIVEN
        String first = "a";
        String second = "";
        String third = "5";
        //WHEN
        Optional<MenuOption> actual1 = underTest.interpretMenuInput(first);
        Optional<MenuOption> actual2 = underTest.interpretMenuInput(second);
        Optional<MenuOption> actual3 = underTest.interpretMenuInput(third);
        //THEN
        assertTrue(actual1.isEmpty());
        assertTrue(actual2.isEmpty());
        assertTrue(actual3.isEmpty());
    }

}
