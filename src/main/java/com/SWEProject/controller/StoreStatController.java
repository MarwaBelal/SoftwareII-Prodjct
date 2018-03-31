/*package com.SWEProject.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SWEProject.Repositories.StatRepository;


public class StoreStatController extends StatisticsController {

	
		@Autowired
		StatRepository repoS;
		
		@Override
		public double sum(String s, String store) { 
			// TODO Auto-generated method stub
			double sum = 0;
			List<Integer> arr = repoS.findColumnStore();
			for (int i=0 ; i<arr.size(); i++)
			{
				sum += arr.get(i);
			}
			return sum;
		}

		@Override
		public double avg(String s, String store) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double max(String s, String store) {
			List<Integer> arr = repoS.findColumnStore();
			double maximum = Collections.max(arr);
			return maximum;
		}

		@Override
		public double min(String s, String store) {
			// TODO Auto-generated method stub
			List<Integer> arr = repoS.findColumnStore();
			double minimum = Collections.min(arr);
			return minimum;
		}
	 
		
	}


*/
