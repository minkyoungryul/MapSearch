package com.kakaobank.project.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Documents {
	
	@JsonProperty("address_name")
	private String addressNm;
	
	@JsonProperty("category_group_code")
	private String ctegryGrpCd;
	
	@JsonProperty("category_group_name")
	private String ctegryGrpNm;
	
	@JsonProperty("category_name")
	private String ctegryNm;
	
	@JsonProperty("distance")
	private String distance;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("place_name")
	private String placeNm;
	
	@JsonProperty("place_url")
	private String placeUrl;
	
	@JsonProperty("road_address_name")
	private String roadAddressNm;
	
	@JsonProperty("x")
	private String x;
	
	@JsonProperty("y")
	private String y;

	public String getAddressNm() {
		return addressNm;
	}

	public void setAddressNm(String addressNm) {
		this.addressNm = addressNm;
	}

	public String getCtegryGrpCd() {
		return ctegryGrpCd;
	}

	public void setCtegryGrpCd(String ctegryGrpCd) {
		this.ctegryGrpCd = ctegryGrpCd;
	}

	public String getCtegryGrpNm() {
		return ctegryGrpNm;
	}

	public void setCtegryGrpNm(String ctegryGrpNm) {
		this.ctegryGrpNm = ctegryGrpNm;
	}

	public String getCtegryNm() {
		return ctegryNm;
	}

	public void setCtegryNm(String ctegryNm) {
		this.ctegryNm = ctegryNm;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlaceNm() {
		return placeNm;
	}

	public void setPlaceNm(String placeNm) {
		this.placeNm = placeNm;
	}

	public String getPlaceUrl() {
		return placeUrl;
	}

	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}

	public String getRoadAddressNm() {
		return roadAddressNm;
	}

	public void setRoadAddressNm(String roadAddressNm) {
		this.roadAddressNm = roadAddressNm;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Documents [addressNm=" + addressNm + ", ctegryGrpCd=" + ctegryGrpCd + ", ctegryGrpNm=" + ctegryGrpNm
				+ ", ctegryNm=" + ctegryNm + ", distance=" + distance + ", id=" + id + ", phone=" + phone + ", placeNm="
				+ placeNm + ", placeUrl=" + placeUrl + ", roadAddressNm=" + roadAddressNm + ", x=" + x + ", y=" + y
				+ "]";
	}
	
}
