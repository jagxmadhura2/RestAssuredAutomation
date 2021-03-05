package util;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter implements ITestListener  {

	private static Object Object = null;
	private Logger logger = Logger.getLogger(this.getClass());
	public static java.lang.Object object;
	public int skipcounter =1;
	public  int failcounter =1;
	public int passcounter =1;
	public  static String class_name;

	/*static SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
	static String logfolderName=sdf1.format(Calendar.getInstance().getTime())+".log";*/
	static  SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH-mm-ss");
	public static String logfileName=sdf.format(Calendar.getInstance().getTime())+"-zeeapitesting.log";

	static void getValueForLogFile()
	{
		//Assign runtime names for log folder and file.

		//System.setProperty("logfoldername",logfolderName);
		System.setProperty(logfileName,"logfileName");
		System.out.println(logfileName);
	}

	//public static String JSONRequestBody;
	//List passedtests = (List) new ArrayList<ITestNGMethod>();

	//List failedtests = (List) new ArrayList<ITestNGMethod>();
	//List skippedtests = (List) new ArrayList<ITestNGMethod>();
	java.util.List<ITestResult> passedtests =  getPassedTests();
	java.util.List<ITestResult> failedtests =  getFailedTests();
	java.util.List<ITestResult> skippedtests = getSkippedTests();

	public int passedtest= passedtests.size();
	public int failedtest= failedtests.size();
	public int skippedtest= skippedtests.size();

	public String path = "bvt/app/TestcaseTree/2.CreateSystem.json";

	public static   String payloadrequest= null;
	public  static String payloadresponse= null;

	public static  String getclass(String classname){
		class_name= classname;

		return class_name;
	}
	public static  String sampleresponse(String response){
		payloadresponse = response;
		return payloadresponse;
	}

	public static String samplerequest(String request){
		payloadrequest = request;
		return payloadrequest;
	}
	/*	public static void main(String[] args){

	    ClassLoader classLoader = CustomListener.class.getClassLoader();

	    try {
	        Class aClass = classLoader.loadClass("com.thed.zeeapitesting.resources."+class_name);
	        System.out.println("aClass.getName() = " + aClass.getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	} */

	@Override
	public void onTestStart(ITestResult tr) {
		String replacement = tr.getName();
		/* String path = "bvt\\app\\CreateRelease\\2.CreateSyste.json".replace("CreateSyste", replacement);
	String payload = RestUtil.getPayload(path); */
		//Assign runtime names for log folder and file.
		getValueForLogFile();
		//System.setProperty("logfoldername",logfolderName);
		//System.setProperty("logfileName", logfileName);
		logger.info("+++++++++++++++++++++++++++++++++++++++++");
		logger.info("Test" + tr.getName() + " Started....");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		//Object newObject = Class.forName(class_name).newInstance();
		/*try {
			java.lang.Object Object1 = createclass();
			boolean b=Object1.getClass().getName().equals(Object);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		String response=payloadresponse;
		logger.info("Test '" + tr.getName() + "' PASSED");

		logger.info("Sample Request:" +payloadrequest );
		logger.info("Sample Response:"+ payloadresponse);

		// This will print the class name in which the method is present
		logger.info(tr.getTestClass());

		// This will print the priority of the method.
		// If the priority is not defined it will print the default priority as
		// 'o'
		logger.info("Priority of this method is " + tr.getMethod().getPriority());
		logger.info("+++++++++++++++++++++++++++++++++++++++++");
		//method = tr.getMethod();
		//JSONRequestBody = method.getInstance().toString();
		//logger.info(JSONRequestBody );
		//System.out.println(".....");
	}


	@Override
	public void onTestFailure(ITestResult tr) {

		logger.info("Test '" + tr.getName() + "' FAILED");
		logger.info("Priority of this method is " + tr.getMethod().getPriority());
		//add the failed tests to the failed list


		logger.info("Sample Request:" +payloadrequest );
		logger.info("Sample Response:"+ payloadresponse);

		System.out.println(".....");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.info("Test '" + tr.getName() + "' SKIPPED");
		System.out.println(".....");

		//add the skipped tests to the skipped list

	}
	// This will log sample request and response for tests
	/*private void log(String request, String response) {
		logger.info("Sample Request"+ request);
		logger.info("Sample Response"+ response);
	}*/

	/*public void generateReport(List xmlSuites, List suites, String outputDirectory){

		//Iterating over each suite included in the test

		for (ISuite suite : xmlSuites) {
			//Following code gets the suite name
			String suiteName = suite.getName();
			//Getting the results for the said suite
			Map<String,ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {

				ITestContext tc = sr.getTestContext();
				System.out.println("Passed tests for suite '" + suiteName +
						"' is:" + tc.getPassedTests().getAllResults().size());
				System.out.println("Failed tests for suite '" + suiteName +
						"' is:" +
						tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped tests for suite '" + suiteName +
						"' is:" +
						tc.getSkippedTests().getAllResults().size());
				System.out.println("Total excution time for test '" + tc.getName() +
						"' is:" + (tc.getEndDate().getTime()- tc.getStartDate().getTime()));
			}}
	}*/
	@Override
	public void onFinish(ITestContext testContext) {

		Object[] pass = testContext.getPassedTests().getAllMethods().toArray();
		Object[] fail = testContext.getFailedTests().getAllMethods().toArray();
		Object[] skip = testContext.getSkippedTests().getAllMethods().toArray();

		logger.info("+++++++++++++++++++++++++++++++++++++++++");
		logger.info("---------------Test Summary--------------");
		logger.info("passed tests count:"+ testContext.getPassedTests().size());
		logger.info("failed tests count:"+ testContext.getFailedTests().size());
		logger.info("skipped tests count:"+ testContext.getSkippedTests().size());
		logger.info("-----------------------------------------");
		logger.info("---------------Test Map------------------");
		logger.info("passed tests Map:"+ testContext.getPassedTests());
		logger.info("failed tests Map:"+ testContext.getFailedTests());
		logger.info("skipped tests Map:"+ testContext.getSkippedTests());
		logger.info("-----------------------------------------");
		logger.info("---------------Test List------------------");
		for (Object p: pass) {

			logger.info("Passed tests List:"+"["+ passcounter+"]" +p);
			passcounter++;
		}
		for (Object f: fail) {


			logger.info("Failed tests List:"+"["+failcounter +"]"+ f);
			failcounter++;
		}

		for (Object s: skip) {


			logger.info("Skipped tests List:"+"]"+skipcounter+"]"+ s);
			skipcounter++;
		}

		logger.info("-----------------------------------------");

	}

}

