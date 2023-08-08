package org.gigsoft.secondoblog.model;

import java.sql.Timestamp;
import java.util.List;

import org.gigsoft.secondoblog.dto.BoardDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	@Column(columnDefinition = "text")
	private String content;	
	
	private Integer count;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 기본 FetchType.Lazy UI에 따라 달라짐
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replies;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private Timestamp createdDate;

	public Board patch(BoardDto dto) {
		// TODO Auto-generated method stub
		this.id = dto.id();
		if (dto.title() != null) 
			this.title = dto.title();
		if (dto.content() != null)
			this.content = dto.content();
		
		this.createdDate = new Timestamp(System.currentTimeMillis());		
		return this;
	}
}
