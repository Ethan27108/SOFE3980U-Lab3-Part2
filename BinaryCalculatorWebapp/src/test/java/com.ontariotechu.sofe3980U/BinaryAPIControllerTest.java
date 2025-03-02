package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // New tests
    @Test
    public void add3() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "00000").param("operand2", "00001"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void add4() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "11111").param("operand2", "111"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("100110"));
    }

    @Test
    public void add5() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "101").param("operand2", "1000"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/Multiply").param("operand1", "00000").param("operand2", "00001"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/Multiply").param("operand1", "10").param("operand2", "10"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void multiply3() throws Exception {
        this.mvc.perform(get("/Multiply").param("operand1", "10").param("operand2", "100"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    @Test
    public void OR() throws Exception {
        this.mvc.perform(get("/OR").param("operand1", "0").param("operand2", "0000000"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void OR2() throws Exception {
        this.mvc.perform(get("/OR").param("operand1", "0110").param("operand2", "0000001"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("111"));
    }

    @Test
    public void OR3() throws Exception {
        this.mvc.perform(get("/OR").param("operand1", "1111111").param("operand2", "010101"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1111111"));
    }

    @Test
    public void AND() throws Exception {
        this.mvc.perform(get("/AND").param("operand1", "0").param("operand2", "0000000"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void AND2() throws Exception {
        this.mvc.perform(get("/AND").param("operand1", "0111").param("operand2", "0000001"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void AND3() throws Exception {
        this.mvc.perform(get("/AND").param("operand1", "1111111").param("operand2", "010101"))// .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("10101"));
    }
}
