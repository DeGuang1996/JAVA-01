package Q2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author deguang
 * @date 2021/01/24
 */

public class TestHttpClient {

    private final static Logger logger = LoggerFactory.getLogger(TestHttpClient.class);

    public static void main(String[] args) {
        try {
            // 1.创建一个httpClient对象
            CloseableHttpClient client = HttpClients.createDefault();
            // 2.创建uriBuilder 对于httpClient访问指定页面url必须要使用http://开始
            URIBuilder uriBuilder = new URIBuilder("http://localhost:8801");
            // URIBuilder uriBuilder = new URIBuilder("https://www.baidu.com");
            // 3.创建http get对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 4.设置请求报文头部的编码
            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;utf-8"));
            // 5.设置期望服务返回的编码
            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            // 6.请求服务
            CloseableHttpResponse response = client.execute(httpGet);
            // 7.获取请求返回码
            int statusCode = response.getStatusLine().getStatusCode();
            // 8.如果请求返回码是200，则说明请求成功
            if (statusCode == 200) {
                // 9.获取返回实体
                HttpEntity entity = response.getEntity();
                // 10.通过EntityUtils的一个工具类获取返回的内容
                String str = EntityUtils.toString(entity, "UTF-8");
                System.out.println("请求成功的返回内容:" + str);
            } else {
                System.out.println("请求失败！");
            }
            response.close();
            client.close();
        } catch (Exception e) {
            logger.error("Exception:{}", e.getMessage(), e);
        }
    }
}
