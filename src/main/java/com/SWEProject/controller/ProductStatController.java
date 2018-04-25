package com.SWEProject.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.SWEProject.Repositories.StatRepository;
import com.SWEProject.Repositories.StoresProductsRepository;
import com.SWEProject.Repositories.UserRepository;
@RestController
public class ProductStatController extends StatisticsController{

	/*@Autowired
	public StatRepository Srepo;
	@Autowired
	public StoresProductsRepository repo;
	*/
	
	@Override
	public double sum(String s,String storename, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		if (s.equals("soldout"))
		{
			List<String> soldOuts = new ArrayList<String>();
				soldOuts=repo.findSoldOut(storename);
			double sum=soldOuts.size();
			Srepo.UpdateValue(sum, id);
			return sum;
			//get list  of product name whose quantity equals 0 and get the size
		}
		else if (s.equals("numofbuys"))
		{
			double sum=0;
			List<Integer> numOfBuys = new ArrayList<Integer>();
			numOfBuys = repo.findnumOfBuys(storename);
			for (int i=0 ; i<numOfBuys.size() ; i++) sum=sum+numOfBuys.get(i);
			Srepo.UpdateValue(sum, id);
			return sum;
		}
		else if (s.equals("quantity"))
		{
			double sum=0;

				List<Integer> Qproducts;
				Qproducts=	repo.findquantity(storename);
				for(int i=0 ; i<Qproducts.size() ; i++)
				{
					sum+=Qproducts.get(i);
				}
				Srepo.UpdateValue(sum, id);
			return sum;
		}
		return 0;
	}

	@Override
	public double avg(String s, String store, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		// TODO Auto-generated method stub
		double Avg=0;
		if(s.equals("price"))
		{
			List<Double> Pofproducts=repo.findstoreprice(store);
			for(int i=0 ; i<Pofproducts.size() ; i++)
			{
				Avg+=Pofproducts.get(i);
			}
			Avg=Avg/(Pofproducts.size());
		}
		Srepo.UpdateValue(Avg, id);
		return Avg;
	}

	@Override
	public double max(String s, String storename, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		double maximum=0;
		 if (s.equals("numofbuys"))
		{
			
			List<Integer> numOfBuys = new ArrayList<Integer>();
			numOfBuys = repo.findnumOfBuys(storename);
			maximum = Collections.max(numOfBuys);
			Srepo.UpdateValue(maximum, id);
			return maximum;
		}
		else if (s.equals("quantity"))
		{
			List<Integer> Quantity = new ArrayList<Integer>();
			Quantity = repo.findQuantity(storename);
			maximum = Collections.max(Quantity);
			Srepo.UpdateValue(maximum, id);
			return maximum;
		}
		return 0;
	}

	@Override
	public double min(String s, String storename, int id,StatRepository Srepo,StoresProductsRepository repo,UserRepository Urepo) {
		double minimum=0;
		 if (s.equals("numofbuys"))
		{
			
			List<Integer> numOfBuys = new ArrayList<Integer>();
			numOfBuys = repo.findnumOfBuys(storename);
			minimum = Collections.min(numOfBuys);
			Srepo.UpdateValue(minimum, id);
			return minimum;
		}
		else if (s.equals("quantity"))
		{
			List<Integer> Quantity = new ArrayList<Integer>();
			Quantity = repo.findQuantity(storename);
			minimum = Collections.min(Quantity);
			Srepo.UpdateValue(minimum, id);
			return minimum;
		}
		return 0;
	}
	

	

}
