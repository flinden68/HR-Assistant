package ch.belsoft.hrassistant.controller;

public interface IGuiController<T> {

	public T getDataContext();

	public String getPageTitle();

}