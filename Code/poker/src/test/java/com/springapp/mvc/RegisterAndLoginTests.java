package com.springapp.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poker.entity.LoginEntity;
import com.poker.entity.ReturnEntity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by rjt on 16/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml","classpath:spring-bean.xml" ,"classpath:spring-hibernate.xml"} )
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按照字母升序顺序执行测试方法
public class RegisterAndLoginTests {

    static Date date;
    static String username ;
    static String nickname ;
    static String pwd;

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {

        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @BeforeClass
    public static void initParam(){
        date = new Date();
        username = "test@test.com";
        pwd = "12345678";
        nickname = "牛B";
    }
    //登录测试
    @Test
    public void test_B_Login() throws Exception {
        //登录测试
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login");
        requestBuilder.header("Content-Type", "application/json");

        Map<String,String> param = new HashMap<String,String>();
        param.put("username", username);
        param.put("pwd", pwd);

        ObjectMapper pMapper = new ObjectMapper();
        String pStr = pMapper.writeValueAsString(param);
        System.out.println(date.getTime()+":"+pStr);

        requestBuilder.content(pStr);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String str = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        LoginEntity l2 = mapper.readValue(str, LoginEntity.class);
        Assert.assertEquals(l2.getResult(), ReturnEntity.RETURN_SUCCESS);

        assertThat(Long.valueOf(l2.getUid()), greaterThan(0l));
        assertThat(l2.getNickname(),equalTo(nickname));
        assertThat(l2.getSessionid(), notNullValue());
    }
    //注册测试
    @Test
    public void test_A_Register() throws Exception {
        //注册测试
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/register");
        requestBuilder.header("Content-Type", "application/json");

        Map<String,String> param = new HashMap<String,String>();
        param.put("username",username );
        param.put("pwd", pwd);
        param.put("nickname",nickname);

        ObjectMapper pMapper = new ObjectMapper();
        String pStr = pMapper.writeValueAsString(param);
        System.out.println(date.getTime()+":"+pStr);

        requestBuilder.content(pStr);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String str = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();

        LoginEntity l2 = mapper.readValue(str, LoginEntity.class);
        Assert.assertEquals(l2.getResult(), ReturnEntity.RETURN_SUCCESS);

        assertThat(Long.valueOf(l2.getUid()), greaterThan(0l));
        assertThat(l2.getNickname(),equalTo(nickname));
        assertThat(l2.getSessionid(), notNullValue());

    }
//
//    @Test
//    public void test_B_SSO() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/sso");
////        requestBuilder.header("Content-Type", "application/json");
//        requestBuilder.param("accesstoken", "CEEC44622FD689E77EB7693C43A3633D");
//        requestBuilder.param("openid", "DB6F86EEF4CCF1B79A36ED9DEE8B9089");
//
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        String str = result.getResponse().getContentAsString();
//        System.out.println("str = " + str);
////        ObjectMapper mapper = new ObjectMapper();
////        ProductViewEntity l2 = mapper.readValue(str, ProductViewEntity.class);
////        assertThat(l2.getRecords().size(), greaterThan(0));
////        assertThat(l2.getRecords().size(), lessThanOrEqualTo(3));
//
//    }
//
//    @Test
//    public void test_C_Modify_QQ() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/modifyqq");
//        requestBuilder.header("Content-Type", "application/json");
//        requestBuilder.header("let_come_uid", "9999");
//        Map<String,String> param = new HashMap<String,String>();
//
//        param.put("id","9999");
//        param.put("qq",qq );
//
//        ObjectMapper pMapper = new ObjectMapper();
//        String pStr = pMapper.writeValueAsString(param);
//        System.out.println(pStr);
//
//        requestBuilder.content(pStr);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        String str = result.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//        ReturnEntity l2 = mapper.readValue(str, ReturnEntity.class);
//        Assert.assertEquals(l2.getResult(), ReturnEntity.RETURN_SUCCESS);
//    }

}
