package com.social.web.utils;

public enum URLPattern{
	
	ACTIVE_CLASS(0), ID(1), RELATED_CLASS(2);
	
	private int layer;
	
	URLPattern(int layer) {
		this.layer = layer;
	}
	
	public int getLayer(){
		return layer;		
	}	
}
