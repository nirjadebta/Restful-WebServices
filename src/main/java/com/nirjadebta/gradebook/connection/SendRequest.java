/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirjadebta.gradebook.connection;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;



public class SendRequest {

//private int connectionTimeOut = 10000;

public String SendRequest(String URL, String method, String request) {

BufferedReader reader = null;
try {
// String encryptJSON = AESCrypto.encrypt(requestJson);

// Defined URL where to send data
URL url = new URL (URL);
// Send POST data request
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setReadTimeout(15000);
conn.setConnectTimeout(15000);
conn.setRequestMethod(method);
conn.setDoInput(true);
conn.setDoOutput(true);

//conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

//conn.setRequestProperty("Accept", "application/xml");
conn.setRequestProperty("Content-Type", "application/xml");

if(request!=null){
    OutputStream outputStream = conn.getOutputStream();
    byte[] b = request.getBytes("UTF-8");
    outputStream.write(b);
    outputStream.flush();
    outputStream.close();   
}
//OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//wr.flush();

// Get the server response
reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
StringBuilder sb = new StringBuilder();
String line = null;
// Read Server Response
while ((line = reader.readLine()) != null) {
sb.append(line + "\n");
}
System.out.println(sb.toString());
return sb.toString();
} catch (Exception ex) {
return ex.getMessage();
}
}

}