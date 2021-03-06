package kganesh1795.security_tutorial.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import kganesh1795.security_tutorial.config.SecurityTestConfig;
import kganesh1795.security_tutorial.model.Admin;
import kganesh1795.security_tutorial.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityTestConfig.class)
@AutoConfigureMockMvc
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void get_home_without_user_should_redirect_to_login() throws Exception {
		mockMvc.perform(get("/")).andExpect(redirectedUrlPattern("**/login"));
	}

	@Test
	@WithUserDetails(userDetailsServiceBeanName = "testUserService", value = "testUser")
	public void get_home_with_user_should_return_view_home_and_model_with_username() throws Exception {
		ModelAndView mv = mockMvc.perform(get("/home")).andReturn().getModelAndView();

		assertThat(mv.getViewName(), equalTo("home"));
		assertThat(((User) mv.getModel().get("user")).getUsername(), equalTo("testUser"));
	}
	
	@Test
	@WithUserDetails(userDetailsServiceBeanName = "testAdminService", value = "admin")
	public void get_home_with_login_should_return_view_home_and_model_with_username() throws Exception {
		ModelAndView mv = mockMvc.perform(get("/home")).andReturn().getModelAndView();

		assertThat(mv.getViewName(), equalTo("home"));
		assertThat(((Admin) mv.getModel().get("user")).getUsername(), equalTo("admin"));
	}

}