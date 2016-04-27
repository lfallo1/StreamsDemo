package com.streams.dal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.streams.model.Employee;
import com.streams.model.Position;

public class DataLayer {

	private static final String[] names = new String[]{"Hilton Rozzell", "Velvet Lyles", "Chelsea Calandra", "Zona Mangual", "Myrtle Lindblom", "Jonah Simcox", "Giselle Render", "Esteban Axelrod", "Vickey Gormley", "Carmelia Aden", "Oleta Donati", "Paris Moriarty", "Hui Finck", "Chantell Mutchler", "Joy Broomfield", "Francisca Graden", "Marvella Spurgeon", "Lida Busse", "Brinda Shorey", "Celesta Huckins", "Bradly Keefer", "Latoria Rowton", "Jerrie Santillan", "Isaiah Fritz", "Tressie Hammill", "Lecia Lintz", "Mirta Dowell", "Lanette Soileau", "Celine Shreve", "Darby Pawlowski", "Todd Segrest", "Lai Haslem", "Denver Brownell", "Hermine Mcconnaughy", "Leo Napier", "Patrice Lunn", "Teresa Samora", "Lorenzo Firkins", "Thi Montemurro", "Marina Schoenberg", "Rima Cowart", "Bridgett Hord", "Else Gaymon", "Kristi Motes", "Charise Opitz", "Porsha Defrank", "Aimee Wingham", "Conrad Donegan", "Elissa Byerly", "Collin Kalman", "Yael Barnum", "Stormy Rodi", "Orpha Points", "Valene Selby", "Merri Duell", "Marylyn Woodell", "Gennie Tuten", "Georgina Lattimer", "Paz Huston", "Robbin Chason", "Caroll Eyman", "Carly Burleson", "Kyung Saari", "Tiesha Mcvay", "Cleotilde Matsuda", "Regina Kovach", "Rebecca Bluitt", "Tammy Behler", "Katharyn Williford", "Jacquiline Forkey", "Sun Mingus", "Caprice Crabtree", "Claudie Rauch", "Calandra Macmillan", "Camila Naber", "Letha Sprenger", "Lourdes Wolski", "Jamee Reeve", "Malorie Alphin", "Jinny Oden", "Season Troyer", "Treva Burbach", "Ardelia Baisley", "Ferne Reams", "Rosamaria Steely", "Lenita Quimby", "Adrienne Smelley", "Wilda Corley", "Sherley Ferrigno", "Era Lossett", "Luvenia Hardesty", "Darlene Apo", "Sherly Murdoch", "Youlanda Christopher", "Lisa Kilmon", "Candy Vale", "Bernetta Hug", "Gwenn Ellerman", "Jazmin Breece", "Astrid Check"};
	private static List<Employee> employees;
	private static List<Position> positions;
	private static DataLayer instance;
	
	private DataLayer(){
		initialize();
	}
	
	public static DataLayer getInstance(){
		if(instance == null){
			instance = new DataLayer();
		}
		return instance;
	}
	
	private void initialize(){
		employees = new ArrayList<>();
		final Random r = new Random();
		positions = Arrays.asList(new Position(1, "Manager"), new Position(2, "Developer"), new Position(3, "Sales"));
		for (int i = 0; i < 100; i++) {
			Employee e = new Employee();
			e.setId(i);
			Double rand = r.nextDouble()*2000000000000.0;
			e.setDateOfBirth(new Date((long) (new Date().getTime() - rand)));
			e.setPosition(positions.get(r.nextInt(positions.size())));
			e.setSalary((double) (r.nextInt(1000000) + 25000));
			e.setName(names[r.nextInt(names.length)]);
			employees.add(e);
		}
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	
}
