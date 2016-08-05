package ch.belsoft.hrassistant.controller;

public interface IGuiController<T> {

	public boolean isReadOnly();

	public T getDataContext();

	public String getPageTitle();

}