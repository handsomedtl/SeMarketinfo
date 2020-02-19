package com.bhzq.marketinfo.entity.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChartDataSet implements Cloneable{	
	private String label;
	private String backgroundColor;
	private String borderColor;	
	private boolean fill = false;
	private List<Integer> data;

	
	@Override
	public Object clone() throws CloneNotSupportedException {
		ChartDataSet t = (ChartDataSet) super.clone();
		t.data = null;
		
		return t;
	}


	public static enum ColorType{
		RED(0,"rgb(255, 99, 132)"),GREEN(1,"rgb(75, 192, 192)"),BLUE(2,"rgb(12, 12, 192)"),PINK(3,"rgb(120, 12, 0)")
		,OTHER1(4,"rgb(0, 0, 233)"),OTHER2(5,"rgb(0, 230, 230)"),OTHER3(6,"rgb(104, 12, 0)"),OTHER4(7,"rgb(96, 96, 96)");
		
		private int index;
		private String rgb;
		
		private ColorType(int index,String rgb){
			this.setIndex(index);
			this.setRgb(rgb);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getRgb() {
			return rgb;
		}

		public void setRgb(String rgb) {
			this.rgb = rgb;
		}
		
				
		public static String getColor(int index) {
			ColorType[] colorTypes = values();
	        for (ColorType type : colorTypes) {
	            if (type.getIndex() == index) {
	                return type.getRgb();
	            }
	        }
	        return null;
	    }
		
	}
}


