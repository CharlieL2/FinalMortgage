package pikas;

import java.util.ArrayList;
import java.util.Collections;

public class Engine {

	private double GrossIncome, MonthlyDebt, IntRate, Term, DownPay, HousePay, HousePayPlus, MaxPay, AmtMort;

	
	public Engine(double grossincome, double monthlydebt, double intrate, double term, double downpay){
		
		setGrossIncome(grossincome);
		setMonthlyDebt(monthlydebt);
		setIntRate(intrate);
		setTerm(term);
		setDownPay(downpay);
		
		calcHousePay();
		calcHousePayPlus();
		calcMaxPay();
		calcAmtMort();
		
	}

	private void calcHousePay(){
		HousePay = (GrossIncome / 12) * 0.18;
	}
	
	private void calcHousePayPlus(){
		HousePayPlus = ((GrossIncome / 12) * 0.36) - MonthlyDebt;
	}
	
	//finds the smaller value of the two
	private void calcMaxPay(){
		ArrayList<Double> Pays = new ArrayList<Double>(); 
		Pays.add(HousePay);
		Pays.add(HousePayPlus);
		MaxPay = Collections.min(Pays);
	}
	
	private void calcAmtMort(){
		double MonthInt = IntRate / 12;
		double NumPay = Term * 12;
		AmtMort = (MaxPay * ((1 - Math.pow((1 + MonthInt),-NumPay))/(MonthInt))) + DownPay; 
	}
	
	//setters and getters
	public double getGrossIncome() {
		return GrossIncome;
	}

	private void setGrossIncome(double grossIncome) {
		GrossIncome = grossIncome;
	}

	public double getMonthlyDebt() {
		return MonthlyDebt;
	}

	private void setMonthlyDebt(double monthlyDebt) {
		MonthlyDebt = monthlyDebt;
	}

	public double getIntRate() {
		return IntRate;
	}

	private void setIntRate(double intRate) {
		IntRate = intRate;
	}

	public double getDownPay() {
		return DownPay;
	}

	private void setDownPay(double downPay) {
		DownPay = downPay;
	}

	public double getHousePay() {
		return HousePay;
	}

	public double getHousePayPlus() {
		return HousePayPlus;
	}

	public double getMaxPay() {
		return MaxPay;
	}

	public double getAmtMort() {
		return AmtMort;
	}

	public double getTerm() {
		return Term;
	}

	private void setTerm(double term) {
		Term = term;
	}
}
