package com.example;

import java.util.Date;

public class Threadnew 
{
	class ThreadA extends Thread
	{
		private Date runtime;
		public void run()
		{
			System.out.println("ThreadA begin.");
			try {
				Thread.sleep(1000);
				runtime = new Date();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			System.out.println("ThreadA end."+runtime);
		}
	}
	
	class ThreadB implements Runnable
	{
		private Date runtime;
		public void run()
		{
			System.out.println("ThreadB begin.");
			runtime = new Date();
			System.out.println("ThreadB end."+ runtime);
		}
	}

	public void starta()
	{
		Thread threada = new ThreadA();
		threada.start();
	}

	public void startb()
	{
		Runnable threadb = new ThreadB();
		Thread thread = new Thread(threadb);
		thread.start();
	}
	
	public static void main(String[] args)
	{
		Threadnew test = new Threadnew();
		test.starta();
		test.startb();
	}
	
	
}
