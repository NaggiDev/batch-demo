package com.javainuse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		MainApp obj = new MainApp();
		obj.runBatchTask();
	}

	private void runBatchTask() {

		//load the batch config file
		String[] batchConfig = { "batch-task.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(
				batchConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		//load the job bean
		Job job = (Job) context.getBean("deleteTask");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Completed");
	}
}