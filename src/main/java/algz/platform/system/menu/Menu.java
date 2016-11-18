package algz.platform.system.menu;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;


/**
 * Hibernate 注解 自关联 : menus parentMenu
 * @author algz
 *
 */

@Entity
@Table(name="algz_nav_menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@GeneratedValue(generator = "paymentableGenerator")
//	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	private Long  id;
	
	/**
	 * 菜单文本
	 */
	@Column(name="TEXT")
	private String text;
	
	/**
	 * 菜单ICON
	 */
	@Column(name="ICON")
	private String icon;
	
	/**
	 * 关联的URL
	 */
	@Column(name="URL")
	private String url;
	
	/**
	 * 是否当前菜单
	 */
	@Transient
	private String active;
	
	/**
	 * 菜单顺序
	 */
	@Column(name="ORDER")
	private Integer order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@JoinColumn(name = "PARENT_ID")
	 private Menu parentMenu;
	
	//@Transient
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "parentMenu")
	 @LazyCollection(LazyCollectionOption.FALSE)
	private Set<Menu> menus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public Menu getParentMenu() {
		return parentMenu;
	}
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
}