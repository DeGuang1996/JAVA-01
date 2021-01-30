package gateway.router;

import java.util.*;

/**
 * @author deguang
 * @date 2021/01/31
 */

public class WeightRandomRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        Map<String, Integer> serverMap = new HashMap<>();
        if (endpoints.size() < 2) {
            return null;
        }
        serverMap.put(endpoints.get(0), 3);
        serverMap.put(endpoints.get(1), 7);

        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }
        Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());

        return serverList.get(randomPos);
    }
}
