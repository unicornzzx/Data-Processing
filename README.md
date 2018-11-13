## Introduction & Declaration 
This repository is a record of my previous work done for Advanced OO programming course. It's a processing tool which can read data from [the dataset](Dataset_RG.xlsx) and do simple analysis and recommendation for the data.

**Notice: this repository only contains source code and the date set. Directly download / clone this repository will not get a compilable Java project.**

People who want to run this program can download all source code and use they to build a Java project.
> 2 more things for these people   
> 1. Change the file path to your excel file in main class: CSE210CW   
> line 23: String fileName = "C:/Users/Unicorn/Desktop/Dataset_RG.xlsx";
> 
> 2. Install external libraies   
> Apache POI to readfile:
>  https://poi.apache.org/download.html#POI-3.17 ,build classpath of them   
>  Maybe need to redownload:  
>  Collections4-4.1 http://commons.apache.org/proper/commons-collections/download_collections.cgi   
>  xmlbeans-2.6.0 http://book2s.com/java/jar/x/xmlbeans/download-xmlbeans-2.6.0.jar.html)

### About the dataset
The dataset consists of records of researchers crawled from the website ResearchGate (https://www.researchgate.net, thanks to Mr. Hang Dong, PhD candidate at XJTLU, for sharing the dataset). In total, there are more than 6,000 researchers and topics+skills. Each record (row) contains information about a researcher; this processing tool only focus on the following columns:
* university
* department
* users' name
* topic and skill (together they are referred to as research interests or interests). Interests under these two columns are delimited with a comma (,).

### About this program
**This processing tool follows the object-oriented programming principles well on following points:**
* **object modelling**
* **functionality decomposition**
* **code reuse**

**Also good at correctness, robustness and efficiency. In addition, the code was commented by javadoc comments, you will see the whole javadoc documentation if you generate the javadoc after building a Java project by using the source code provided by this repository.**



