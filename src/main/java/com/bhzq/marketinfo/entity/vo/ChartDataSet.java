package com.bhzq.marketinfo.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChartDataSet {	
	private String label;
	private String backgroundColor;
	private String borderColor;	
	private boolean fill = false;

	
	public static enum ColorType{
		RED(1,"rgb(255, 99, 132)"),GREEN(2,"rgb(75, 192, 192)"),BLUE(2,"rgb(12, 12, 192)"),PINK(3,"rgb(120, 12, 0)");
		
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


