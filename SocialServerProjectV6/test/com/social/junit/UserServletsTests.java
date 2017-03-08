package com.social.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.social.jpa.utils.BeansConfigure;
import com.social.web.servlets.UserServlet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,
classes={BeansConfigure.class})
public class UserServletsTests {

	private static final String FIRST_NAME = "MockUser";
	private static final String LAST_NAME = "Mocker";

	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;


	PrintWriter writerReq1;
	PrintWriter writerReq2;
	File testFile1;
	File testFile2;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);

		testFile1 = new File("testTemp.txt");
		testFile1.deleteOnExit();					
		writerReq1 = new PrintWriter("testTemp.txt");


		testFile2 = new File("testTemp2.txt");
		testFile2.deleteOnExit();

		writerReq2 = new PrintWriter("testTemp2.txt");
	}


	@Test
	public void testMockCreation() {

		assertNotNull(request);
		assertNotNull(response);
	}

	@Test
	public void testUserPostAndGet() throws IOException, ServletException {

		FileReader fr = new FileReader(testFile1);
		BufferedReader brReq1 = new BufferedReader(fr);

		when(request.getParameter("firstName")).thenReturn(FIRST_NAME);
		when(request.getParameter("lastName")).thenReturn(LAST_NAME);
		when(response.getWriter()).thenReturn(writerReq1);        

		new UserServlet().doPost(request, response);       

		when(request.getPathInfo()).thenReturn("/" + brReq1.readLine());      
		when(response.getWriter()).thenReturn(writerReq2);     

		new UserServlet().doGet(request, response);             

		assertTrue(FileUtils.readFileToString(testFile2, "UTF-8")
				.contains(FIRST_NAME));	

		assertTrue(FileUtils.readFileToString(testFile2, "UTF-8")
				.contains(LAST_NAME));									


	}


}
