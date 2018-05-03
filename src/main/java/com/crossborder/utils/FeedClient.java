package com.crossborder.utils;

import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/// <summary>
/// 上传数据客户端
/// </summary>
public class FeedClient {

    private FeedClient() {
    }

    public FeedClient(String feedType) {
        this.FeedType = feedType;
    }

    /// <summary>
    /// 上传类型
    /// </summary>
    String FeedType;

    /// <summary>
    /// 获得账户信息
    /// </summary>
    Account Account;

    public String getFeedType() {
        return FeedType;
    }

    public void setFeedType(String feedType) {
        FeedType = feedType;
    }

    public Account getAccount() {
        return Account;
    }

    public void setAccount(Account account) {
        Account = account;
    }

    private MarketplaceWebServiceConfig GetConfig() {
        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
        config.setServiceURL(Account.getServiceUrl());
        return config;
    }

    private MarketplaceWebServiceClient GetClient() {
        MarketplaceWebServiceConfig config = this.GetConfig();
        MarketplaceWebServiceClient client = new MarketplaceWebServiceClient(Account.getAppName(),
                Account.getAppVersion(), Account.getAccessKeyId(), Account.getSecretAccessKey(), config);
        return client;
    }

    /// <summary>
    /// Step 1:  提交XML或txt 上传文件，亚马逊服务端接受到数据，返回一个FeedSubmissionId
    /// </summary>
    /// <returns></returns>
    public String SubmitFeed() throws MarketplaceWebServiceException {
        MarketplaceWebServiceClient client = GetClient();
        SubmitFeedRequest request = new SubmitFeedRequest();
        request.setFeedType(this.FeedType);       //!上传商品数据
        request.setMarketplaceIdList(new IdList());
        request.getMarketplaceIdList().setId(Account.getMarketIds());

        request.setMerchant(Account.getMerchantId());

        //request.setFeedContent(new FileInputStream(new File("")));
        //request.setFeedContent();

        SubmitFeedResponse response = client.submitFeed(request);
        SubmitFeedResult result = response.getSubmitFeedResult();
        return result.getFeedSubmissionInfo().getFeedSubmissionId();
    }

    /// <summary>
    /// Step 2: 提交一个SubmissionList，等待亚马逊返回"_DONE"状态，如果没有返回则一直等待。
    /// </summary>
    /// <param name="feedSubmissionId">feedSubmissionId</param>
    /// <returns></returns>
    public boolean GetFeedSubmissionList(String feedSubmissionId) throws MarketplaceWebServiceException {
        boolean isSuccess = true;
        MarketplaceWebServiceClient client = GetClient();
        GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
        request.setFeedSubmissionIdList(new IdList());
        List<String> feedSubmissionIds=new ArrayList<>();
        feedSubmissionIds.add(feedSubmissionId);
        request.getFeedSubmissionIdList().setId(feedSubmissionIds);

        while (isSuccess) {
            GetFeedSubmissionListResponse response = client.getFeedSubmissionList(request);
            GetFeedSubmissionListResult result = response.getGetFeedSubmissionListResult();

            for(FeedSubmissionInfo info: result.getFeedSubmissionInfoList())
            {
                if (info.getFeedProcessingStatus() .equals("_Done")) {
                    isSuccess = false;
                } else {
                    try {
                        Thread.sleep(2 * 60 * 1000);   //! 休息一会。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return isSuccess;
    }

    /// <summary>
    ///  Step 3: 获得上传结果，如果没有错，亚马逊服务端返回处理报告，否则返回错误的上传数据内容。
    /// </summary>
    /// <param name="feedSubmissionId">feedSubmissionId</param>
    /// <returns></returns>
    public boolean GetFeedSubmissionResult(String feedSubmissionId) throws MarketplaceWebServiceException {
        MarketplaceWebServiceClient client = GetClient();
        GetFeedSubmissionResultRequest request = new GetFeedSubmissionResultRequest();
        request.setFeedSubmissionId(feedSubmissionId);
        //request.setFeedSubmissionResultOutputStream(new FileInputStream());
        request.setMerchant(Account.getMerchantId());

        GetFeedSubmissionResultResponse response = client.getFeedSubmissionResult(request);
        if (response.isSetGetFeedSubmissionResultResult()) {
            GetFeedSubmissionResultResult result = response.getGetFeedSubmissionResultResult();
            if (result.getMD5Checksum()!=null) {
                return true;
            }
        }
        return false;
    }

    /// <summary>
    /// 整合上传数据功能
    /// </summary>
    public boolean SubmitFile() throws MarketplaceWebServiceException {
        String feedSubmissionId = SubmitFeed();
        if (StringUtils.isNotBlank(feedSubmissionId)) {
            if (GetFeedSubmissionList(feedSubmissionId)) {
                return GetFeedSubmissionResult(feedSubmissionId);
            }
        }
        return false;
    }


    class Account {
        private String appName;
        private String appVersion;
        private String accessKeyId;
        private String secretAccessKey;
        private String merchantId;
        private String serviceUrl;
        private List<String> marketIds;

        public List<String> getMarketIds() {
            return marketIds;
        }

        public void setMarketIds(List<String> marketIds) {
            this.marketIds = marketIds;
        }

        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecretAccessKey() {
            return secretAccessKey;
        }

        public void setSecretAccessKey(String secretAccessKey) {
            this.secretAccessKey = secretAccessKey;
        }
    }
}