/**
 * ChatEcho.java 2012-8-9下午6:49:04
 */
package test.robot.module.login;


import org.junit.BeforeClass;
import org.junit.Test;

import test.robot.*;
import test.robot.step.*;

/**
 * @author ddoq
 * @version 1.0.0
 *
 */
public class ChatEcho50
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		new RobotConfig();
	}

	@Test
	public void Normal()
	{
		TimeBatchRun r = new TimeBatchRun(10*60*1000, 1000, 10*60*1000);
		r.SetStep(new ChatEchoStep(50));
		r.run();
	}
}
