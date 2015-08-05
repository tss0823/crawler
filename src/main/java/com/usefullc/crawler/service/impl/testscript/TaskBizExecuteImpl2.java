package com.usefullc.crawler.service.impl.testscript;

import com.usefullc.crawler.common.task.CThread;
import com.usefullc.crawler.common.task.TaskContext;
import com.usefullc.crawler.service.abst.AbstTaskBizExecute;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shengshan.tang on 8/4/2015 at 6:17 PM
 */
public class TaskBizExecuteImpl2 extends AbstTaskBizExecute {

    private final static Logger log = LoggerFactory.getLogger(TaskBizExecuteImpl2.class);


    Queue<CpDetail>     cpDetailQueue = new ConcurrentLinkedQueue<CpDetail>();

    AtomicInteger errDataSize = new AtomicInteger();


    @Override
    public void init(Map<String, Object> initMap) {
        super.init(initMap);
        parseResult = new ConcurrentLinkedQueue<CpDetail>();
        cpDetailQueue = (Queue<TaskBizExecuteImpl2.CpDetail>) parseResult;
    }

    @Override
    public void afterReq(TaskContext taskContext) {
        try{
            Connection.Response response = taskContext.getResponse();
            CThread cThread  = taskContext.getcThread();

            Document rootDoc = response.parse();

            if (rootDoc.select("#inputCode").size() > 0) {
                log.error("屏蔽了");
                //检验屏蔽 url port TODO
                cThread.getEc().shutdownNow();
            }
            Elements eles = rootDoc.select("#qrcode");

            if (eles == null || eles.size() == 0) {
                errDataSize.incrementAndGet();
                return;
            }
            log.info("detailUrl" + taskContext.getUrl());
            String cpName = rootDoc.select("h1").first().text();
            Elements elesTr = rootDoc.select("div.basicMsg").select("table").select("tr");
            String industry = elesTr.get(0).select("td").get(1).text();
            String property = elesTr.get(1).select("td").get(0).text();
            String scale = elesTr.get(1).select("td").get(1).text();
            String contact2 = elesTr.get(2).select("th").get(0).text();
            if (!contact2.equals("联系人")) {
                errDataSize.incrementAndGet();
                return;
            }
            String contact = elesTr.get(2).select("td").get(0).text();
            String phone = elesTr.get(2).select("td").get(1).text();
            String address = elesTr.get(4).select("td").get(0).select("span").text();
            //        System.out.println(cpName+"|"+industry+"|"+property+"|"+scale+"|"+contact+"|"+phone+"|"+address);
            CpDetail cpDetail = new CpDetail();
            cpDetail.setName(cpName);
            cpDetail.setSource("58");
            cpDetail.setIndustry(industry);
            cpDetail.setProperty(property);
            cpDetail.setContact(contact);
            cpDetail.setAddress(address);
            cpDetail.setScale(scale);
            cpDetail.setNetwork(taskContext.getUrl());
            cpDetailQueue.offer(cpDetail);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void beforeReq(TaskContext taskContext) {
    }

    @Override
    public void terminated() {
        log.info("urlQueue size="+cpDetailQueue.size());
        super.terminated();
    }

    class CpDetail implements Serializable {


        private Long id;

        private String name;

        private String industry;
        private String property;
        private String scale;
        private String contact;
        private String address;
        private String phone;
        private String source;
        private String network;

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

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getNetwork() {
            return network;
        }

        public void setNetwork(String network) {
            this.network = network;
        }
    }

}
