package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
	
	private Integer routeId;
	private String source;
	private String destination;
	private Float distance;
	private Integer duration;

}
