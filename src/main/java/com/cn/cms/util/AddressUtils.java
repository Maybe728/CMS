package com.cn.cms.util;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Enumeration;

/**
 * 根据IP地址获取详细的地域信息
 * 淘宝API : http://ip.taobao.com/service/getIpInfo.php?ip=106.239.255.250
 * @since 2018-10-01
 * @author xieke
 */
public class AddressUtils {
	public static final String INTRANET_IP = getIntranetIp(); // 内网IP
	public static final String INTERNET_IP = getInternetIp(); // 外网IP
	public static final byte[] INTERNET_IP_BYTES = getInternetIpBytes();//外网ip字节数组
	public static final byte[] INTRANET_IP_BYTES = getIntranetIpBytes(); // 内网IP字节数组
	/**
	 * 
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encodingString
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getAddresses(String content, String encodingString) {
		// 调用淘宝API
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 取得IP所在的省市区信息
		String returnStr = getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			String[] temp = returnStr.split(",");
			if(temp.length < 3){
				return "0";//无效IP，局域网测试
			}
			return returnStr;
		}
		return "局域网测试";
	}
	/**
	 * @param urlStr
	 *            请求的地址
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码 如GBK,UTF-8等
	 * @return
	 */
	private static String getResult(String urlStr, String content, String encoding) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlStr);
			// 新建连接实例
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接超时时间，单位毫秒
			connection.setConnectTimeout(2000);
			// 设置读取数据超时时间，单位毫秒
			connection.setReadTimeout(2000);
			// 是否打开输出流 true|false
			connection.setDoOutput(true);
			// 是否打开输入流true|false
			connection.setDoInput(true);
			// 提交方法POST|GET
			connection.setRequestMethod("POST");
			// 是否缓存true|false
			connection.setUseCaches(false);
			// 打开连接端口
			connection.connect();
			// 打开输出流往对端服务器写数据
			DataOutputStream out = new DataOutputStream(connection
					.getOutputStream());
			// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.writeBytes(content);
			// 刷新
			out.flush();
			// 关闭输出流
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), encoding));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}

	public static String getAddressByIp(String ipAddress){

		String json_result = AddressUtils.getAddresses("ip=" + ipAddress, "utf-8");
		JSONObject dataJson = JSONObject.parseObject(json_result).getJSONObject("data");
		System.out.println("dataJson： " + dataJson);
		String country = dataJson.getString("country");
		String region = dataJson.getString("region");
		String city = dataJson.getString("city");
		String county = dataJson.getString("county");
		String isp = dataJson.getString("isp");
		String area = dataJson.getString("area");
		System.out.println("国家： " + country);
		System.out.println("地区： " + area);
		System.out.println("省份: " + region);
		System.out.println("城市： " + city);
		System.out.println("区/县： " + county);
		System.out.println("互联网服务提供商： " + isp);
		String address = country + " ";
		address += region + " ";
		address += city + " ";
		//address += county;
		System.out.println(address);
		return address;
	}
	/**
	 * 获得内网IP
	 * @return 内网IP
	 */
	private static String getIntranetIp(){
		try{
			return InetAddress.getLocalHost().getHostAddress();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得外网IP
	 * @return 外网IP
	 */
	private static String getInternetIp(){
		try{
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			Enumeration<InetAddress> addrs;
			while (networks.hasMoreElements())
			{
				addrs = networks.nextElement().getInetAddresses();
				while (addrs.hasMoreElements())
				{
					ip = addrs.nextElement();
					if (ip != null
							&& ip instanceof Inet4Address
							&& ip.isSiteLocalAddress()
							&& !ip.getHostAddress().equals(INTRANET_IP))
					{
						return ip.getHostAddress();
					}
				}
			}

			// 如果没有外网IP，就返回内网IP
			return INTRANET_IP;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得外网IP
	 * @return 外网IP
	 */
	private static byte[] getInternetIpBytes(){
		return ipv4Address2BinaryArray(getInternetIp());
	}

	/**
	 * 获取内网ip
	 * @return
	 */
	private static byte[] getIntranetIpBytes() {
		return ipv4Address2BinaryArray(getIntranetIp());
	}

	/**
	 * 将给定的用十进制分段格式表示的ipv4地址字符串转换成字节数组
	 * @param ipAdd
	 * @return
	 */
	private static byte[] ipv4Address2BinaryArray(String ipAdd){
		byte[] binIP = new byte[4];
		String[] strs = ipAdd.split("\\.");
		for(int i=0;i<strs.length;i++){
			binIP[i] = (byte) Integer.parseInt(strs[i]);
		}
		return binIP;
	}
	// 测试
	public static void main(String[] args) {
		String ip = "106.239.255.250";
		getAddressByIp(ip);
	}

}