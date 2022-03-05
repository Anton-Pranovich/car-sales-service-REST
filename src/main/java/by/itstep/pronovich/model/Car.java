package by.itstep.pronovich.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@Entity
public class Car {
	@JsonIgnore
	private @Id @GeneratedValue Long id;
	@NotNull
	@ApiModelProperty(notes = "Car Name",name="name",required=true,value="test name")
	private String name;
	@NotNull
	@ApiModelProperty(notes = "Car cost",name="cost",required=true,value="1.0")
	private Double cost;

	public Car() {
	}

	public Car(String name, Double cost) {
		this.name = name;
		this.cost = cost;
	}

	public Car(Long id, String name, Double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", cost=" + cost + "]";
	}

}
