
package com.SWEProject.controller;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;


@Controller
public class UserStatController extends StatisticsController {

	
	
	@Override
	public double sum(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) { 
		// TODO Auto-generated method stub
		 if (s.equals("numoflogin"))
		{
		double sum = 0;
		List<Integer> arr = Urepo.AllLogin();
		for (int i=0 ; i<arr.size(); i++)
		{
			sum += arr.get(i);
		}
		Srepo.UpdateValue(sum, id);
		return sum;}
		 return 0;
	}

	@Override
	public double avg(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double max(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		if (s.equals("numoflogin"))
		{
		double maximum = 0;
		List<Integer> arr = Urepo.AllLogin();
		maximum = Collections.max(arr);
		Srepo.UpdateValue(maximum, id);
		return maximum;
		}
		return 0;
	}

	@Override
	public double min(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		if (s.equals("numoflogin"))
		{
		double minimum = 0;
		List<Integer> arr = Urepo.AllLogin();
		minimum = Collections.min(arr);
		Srepo.UpdateValue(minimum, id);
		return minimum;
		}
		return 0;
	}
 
	
}
