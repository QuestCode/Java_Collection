/*
 Devontae Reid
 CIS 282
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;

public class CraiglistScraper extends JFrame {
    
    private int iterator = 0;
    private int max = 0;
    
	// User Input
	private JTextField userSearchTextField;
	private JButton searchButton;
    private ArrayList<CraiglistData> data = new ArrayList<CraiglistData>();
	
	// Results
    private JLabel imagePanel;
	private ImageIcon image;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel locationLabel;
    private JLabel dateLabel;
    private JButton nextButton,backButton;
	
	private String phoneSearch;
	
	CraiglistScraper() {
		super("Craiglist Scraper");
        getContentPane().setLayout(null);
		setSize(500,250);
        setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        panelSetup();
		
		setVisible(true);
	}
	
	private void panelSetup() {
        
        JLabel infoLabel = new JLabel("Search through items on Craiglist with ease!");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(6,29,488,19);
        add(infoLabel);
        
		userSearchTextField = new JTextField();
        userSearchTextField.setBounds(39, 60, 252, 28);
        userSearchTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            
            public void changed() {
                if (userSearchTextField.getText().equals("")){
                    searchButton.setEnabled(false);
                }
                else {
                    searchButton.setEnabled(true);
                }
                
            }
        });
        add(userSearchTextField);
        userSearchTextField.setColumns(10);
		
		searchButton = new JButton("Search");
        searchButton.setEnabled(false);
        searchButton.setBounds(332, 60, 117, 29);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                data = new ArrayList<CraiglistData>();
                iterator = 0;
                openConnection(userSearchTextField.getText());
                userSearchTextField.setText("");
                checkButtonVisiblity(iterator);
            }
        });
        add(searchButton);
        
        resultsPanel();
	}
    
    private void resultsPanel() {
        
        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBounds(6, 100, 488, 19);
        add(nameLabel);
        
        priceLabel = new JLabel();
        priceLabel.setBounds(39, 131, 90, 19);
        add(priceLabel);
        
        locationLabel = new JLabel();
        locationLabel.setBounds(141, 131, 308, 19);
        add(locationLabel);
        
        dateLabel = new JLabel();
        dateLabel.setBounds(39,162,200,19);
        add(dateLabel);
        
        backButton = new JButton("<< Back");
        backButton.setEnabled(false);
        backButton.setBounds(81, 184, 117, 29);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iterator--;
                checkButtonVisiblity(iterator);
                updateData(data.get(iterator));
            }
        });
        add(backButton);
        
        nextButton = new JButton("Next >>");
        nextButton.setEnabled(false);
        nextButton.setBounds(294, 184, 117, 29);
        add(nextButton);
        
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iterator++;
                checkButtonVisiblity(iterator);
                updateData(data.get(iterator));
            }
        });
    }
    
    private void checkButtonVisiblity(int i) {
        if(i == 0) {
            nextButton.setEnabled(true);
            backButton.setEnabled(false);
        } else if(i == max-1) {
            nextButton.setEnabled(false);
            backButton.setEnabled(true);
        } else if (i == 0 && max == 0) {
            nextButton.setEnabled(false);
            backButton.setEnabled(false);
        }else {
            nextButton.setEnabled(true);
            backButton.setEnabled(true);
        }
    }
    
    public void updateData(CraiglistData cld) {
        nameLabel.setText(String.format("%s",cld.getName()));
        priceLabel.setText(String.format("Price: %s",cld.getPrice()));
        locationLabel.setText(String.format("Location: %s",cld.getLocation()));
        dateLabel.setText(String.format("Date Added: %s",cld.getDate()));
    }

	private void openConnection(String search) {
		
		try {  
			String searchUrl = "https://losangeles.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(search, "UTF-8");
            URL site = new URL(searchUrl);
            HttpURLConnection c = (HttpURLConnection) site.openConnection();
            c.setRequestMethod("GET");
            c.connect();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder sb = new StringBuilder();
            
            String line = null;
            
            while((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            HTMLParser parse = new HTMLParser(sb.toString());
            nextButton.setEnabled(true);
            max = parse.getData().size();
            checkButtonVisiblity(iterator);
            
            for(int i = 0; i < max; i++) {
                data.add(parse.getData().get(i));
            }
            updateData(data.get(iterator));
            
		}catch(Exception e){
			 e.printStackTrace();
		}
	}
	

    
    
    /*----------------MAIN_METHOD----------------*/
    public static void main(String[] args) {
        new CraiglistScraper();
    }
	
}


class CraiglistData {
    private String itemName;
    private String itemPrice;
    private String itemLocation;
    private String itemDate;
    
    CraiglistData(String name, String price,String location,String date) {
        this.itemName = name;
        this.itemPrice = price;
        this.itemLocation = location;
        this.itemDate = date;
    }
    
    public void setName(String name) {
        this.itemName = name;
    }
    
    public String getName() {
        return this.itemName;
    }
    
    public void setPrice(String price) {
        this.itemPrice = price;
    }
    
    public String getPrice() {
        return this.itemPrice;
    }
    
    public void setLocation(String location) {
        this.itemLocation = location;
    }
    
    public String getLocation() {
        return this.itemLocation;
    }
    
    public void setDate(String date) {
        this.itemDate = date;
    }
    
    public String getDate() {
        return this.itemDate;
    }
    
    public String toString() {
        return String.format("Name: %s Price: %s Location: %s Date: %s",this.itemName,this.itemPrice,this.itemLocation,this.itemDate);
    }
    
}

class HTMLParser {
    
    private int count = 0;
    
    private String content;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> prices = new ArrayList<String>();
    private ArrayList<String> locations = new ArrayList<String>();
    private ArrayList<String> dates = new ArrayList<String>();
    private ArrayList<String> images = new ArrayList<String>();
    private ArrayList<CraiglistData> data = new ArrayList<CraiglistData>();
    
    HTMLParser(String content) {
        this.content = content;
        nameMatch();
        priceMatch();
        locationMatch();
        dateMatch();
        createCraigListData();
        
        // In case I want to add the images
        //imageMatch();
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return this.content;
    }
    
    private void createCraigListData() {
        for(int i = 0; i <= 20; i++) {
            data.add(new CraiglistData(names.get(i),prices.get(i),locations.get(i),dates.get(i)));
        }
    }
    
    public ArrayList<CraiglistData> getData() {
        return data;
    }
    
    private void nameMatch() {
        Pattern p = Pattern.compile("<a href=\"(.*?)\" data-id=\"(.*?)\" class=\"result-title hdrlnk\">(.*?)</a>");
        Matcher m = p.matcher(this.content);
        while(m.find()) {
            names.add(m.group(3));
        }
    }
    
    private void priceMatch() {
        Pattern p = Pattern.compile("<span class=\"result-price\">(.*?)</span>");
        Matcher m = p.matcher(this.content);
        int i = 0;
        while(m.find()) {
            if (prices.size() > 0) {
                if (prices.get(i-1).compareTo(m.group(1)) != 0) {
                    prices.add(m.group(1));
                    i++;
                } else {
                    
                }
            } else {
                prices.add(m.group(1));
                i++;
            }
        }
    }
    
    private void locationMatch() {
        Pattern p = Pattern.compile("<span class=\"result-hood\">(.*?)</span>");
        Matcher m = p.matcher(this.content);
        while(m.find()) {
            locations.add(m.group(1));
        }
    }
    
    private void imageMatch() {
        // <img alt="" class="" src="https://images.craigslist.org/00z0z_94CnENxZwXa_300x300.jpg">
        System.out.println(this.content);
        Pattern p = Pattern.compile("<img alt=\"\" class=\"\" src=\"(.*?)\">");
        Matcher m = p.matcher(this.content);
        while(m.find()) {
            //System.out.println(m.group());
            //images.add(m.group(1));
        }
    }
    
    private void dateMatch() {
        Pattern p = Pattern.compile("<time class=\"result-date\" datetime=\"(.*)\" title=\"(.*?)\">(.*?)</time>");
        Matcher m = p.matcher(this.content);
        int i = 0;
        while(m.find()) {
            dates.add(m.group(3));
        }
    }
}



/*
 Create a GUI application that provides dynamic content for a user by culling information from another web site in real-time based on their selection criterion.  The application must extract information from a live web site (meaning the information is dynamic and may change from run to run).  The user must be able to make selections from live content and see live results.  Here are some examples, but you can create anything you want as long as it meets the above requirements and is intuitive for the user to operate (points lost if the UI is opaque).
 
 An app that presents the latest TV listings if the user selects from the available channels.
 An app that presents movie show times if the user selects a title, or what's playing if the user selects an area.
 An app that presents sports scores if the user selects a day, or player stats if the user selects a team.
 An app that shows stock quotes if the user selects the stocks.
 An app that shows exchange rates if the user selects the base currency.
 All of the above, or anything else, is acceptable as long as the user can select from "available values" (that are initially pulled from the web site → don't "hard code" the choices) and these options lead to other results (that are also pulled from the web site when the user makes these selections).  Basically, you are presenting available choices and letting the user choose from among these as a filter to deliver the detailed data in a visually pleasing format in a stand-alone JFrame application.
 
 My example program can pull the html (and imbedded JavaScript) from any web URL, but you need to filter through the returned strings to locate and extract the information you want.  Your research will be to identify how to extract what you want from the streamed html code.  Java provides the String, StringTokenizer and other classes that may assist you in parsing and searching through the web pages for the information you are culling.
 
 Place all the code in one source file and name it anything you want (but follow our naming conventions).  Only attach the java file.
 
 Have a lot of fun on this one,
 Dean.
 
 P.S.  Advance students, especially those that have completed the Unix/Linux class, may find "regular expressions" are available in the Java API that may make the process easier.  You might also work with XML parsers (if you now how) but be sure NOT to include any calls to 3rd party libraries and comment your 3rd party sources (and be sure you completely understand how any code works that you derive from 3rd parties).  The majority of the code should be your own.  This is the final stretch.
*/