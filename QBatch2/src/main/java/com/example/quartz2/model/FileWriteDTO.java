package com.example.quartz2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Class StockPriceDetails.
 *
 * @author ashraf
 */
@Data
@AllArgsConstructor
public class FileWriteDTO
{
	private String stock;
	private String open;
	private String close;
	private String low;
	private String high;
}
