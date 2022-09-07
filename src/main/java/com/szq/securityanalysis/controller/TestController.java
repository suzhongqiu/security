package com.szq.securityanalysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.szq.securityanalysis.common.constant.Constant;
import com.szq.securityanalysis.common.response.RequestResult;
import com.szq.securityanalysis.common.response.ResponseCode;
import com.szq.securityanalysis.pojo.article.Comment;
import com.szq.securityanalysis.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/4/10 18:08
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private CommentService commentService;

    private Queue<String> workQueue = new LinkedList<>();
    // public void go() throws Exception {
    //     // 首先，必需要取得一个Scheduler的引用(设置一个工厂)
    //     SchedulerFactory sf = new StdSchedulerFactory();
    //
    //     //从工厂里面拿到一个scheduler实例
    //     Scheduler sched = sf.getScheduler();
    //
    //     //真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例
    //     JobDetail job = newJob(MyTimeJob.class).withIdentity("job1", "group1").build();
    //     // 定义一个触发器，job 1将每隔执行一次
    //     CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").
    //             withSchedule(cronSchedule("50 47 17 * * ?")).build();
    //
    //     //执行任务和触发器
    //     Date ft = sched.scheduleJob(job, trigger);
    //
    //     //格式化日期显示格式
    //     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    //     System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，" +
    //             "并且以如下重复规则重复执行: " + trigger.getCronExpression());
    //
    //     sched.start();
    // }

    public Object crawlByClass(String url, String divClass) throws IOException {
        Document document = Jsoup.parse(new URL(url), 30000);
        Elements element = document.getElementsByClass(divClass);
        return element;
    }

    public Object crawlById(String url, String divId) throws IOException {
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById(divId);
        return element;
    }

    public static void main(String[] args) throws Exception {
        // TestController testController = new TestController();
        // String url = "https://fred.stlouisfed.org/series/T10Y2Y";
        // String divId = "page-title";
        // Object o = testController.crawlByClass(url, divId);
        // if(o instanceof Element) {
        //     System.out.println(((Element)o).html());
        // } else if (o instanceof Elements) {
        //     System.out.println(((Elements)o).html());
        // }
        // String url = "https://fred.stlouisfed.org/graph/api/" + URLEncoder.encode("series/?obs=true&sid=T10Y2Y", "UTF-8");
         String url = "https://fred.stlouisfed.org/graph/api/series/?obs=true&sid=T10Y2Y";
        //String url = "https://fred.stlouisfed.org/graph/api/series/?id=T10Y2Y&width=1168";
        String paraStr = "{\"hostName\":\"fred.stlouisfed.org\"," +
                "\"series\":[]," +
                "\"chart\":" +
                    "{\"labels\":" +
                        "{\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\"," +
                        "\"subtitle\":\"Source: Federal Reserve Bank of St. Louis\"," +
                        "\"left_axis\":\"Percent\",\"right_axis\":\"\",\"bottom_axis\":\"\"," +
                        "\"bubble-size_axis\":\"\",\"footer\":\"2022 fred.stlouisfed.org\"}," +
                    "\"cosd\":\"2017-05-05\",\"coed\":\"2022-05-05\",\"min_date\":\"1976-06-01\"," +
                    "\"max_date\":\"2022-05-05\",\"frequency\":\"Daily\",\"width\":1168," +
                    "\"height\":450,\"drp\":0,\"stacking\":null,\"txtcolor\":\"#444444\",\"mode\":\"fred\"," +
                    "\"ts\":12,\"tts\":12,\"fo\":\"open sans\",\"x_scale\":\"time\",\"trc\":0,\"nt\":0," +
                    "\"thu\":0,\"bgcolor\":\"#E1E9F0\",\"graph_bgcolor\":\"#ffffff\",\"showLegend\":\"yes\"," +
                    "\"showAxisTitles\":\"yes\",\"zoomType\":\"x\",\"showTooltip\":\"yes\",\"chartType\":\"line\"," +
                    "\"recession_bars\":\"on\",\"showNavigator\":\"true\"," +
                    "\"available_chart_types\":" +
                        "[\"line\",\"area\",\"column\",\"scatter\",\"pie\"," +
                         "\"bubble\"]," +
                    "\"log_scales\":" +
                        "{\"left\":false,\"right\":false,\"bottom\":false," +
                        "\"bubble-size\":false},\"available_stacking\":[\"normal\"," +
                        "\"percent\"],\"legacy_url\":\"bgcolor=%23E1E9F0&chart_type=line&drp=0&fo=open%20sans&graph_bgcolor=%23ffffff&height=450&mode=fred&recession_bars=on&txtcolor=%23444444&ts=12&tts=12&width=1168&nt=0&thu=0&trc=0&show_legend=yes&show_axis_titles=yes&show_tooltip=yes&id=T10Y2Y&scale=left&cosd=2017-05-05&coed=2022-05-05&line_color=%234572a7&link_values=false&line_style=solid&mark_type=none&mw=3&lw=2&ost=-99999&oet=99999&mma=0&fml=a&fq=Daily&fam=avg&fgst=lin&fgsnd=2020-02-01&line_index=0&transformation=lin&vintage_date=2022-05-06&revision_date=2022-05-06&nd=1976-06-01\"," +
                        "\"piedate\":null,\"lastModified\":\"Thu, 05 May 2022 21:01:02 GMT\"," +
                        "\"obsFetch\":false,\"initialPayload\":true}," +
                    "\"xAxis\":{},\"yAxis\":{}," +
                    "\"seriesObjects\":" +
                        "[{\"type\":\"time-series\",\"line_index\":1,\"legendIndex\":1," +
                        "\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\"," +
                        "\"hide_marks\":true,\"available_formula_transformations\":{\"lin\":{\"full\":\"\",\"short\":\"\"}," +
                        "\"chg\":{\"full\":\"Change\",\"short\":\"Chg.\"},\"ch1\":" +
                        "{\"full\":\"Change from Year Ago\",\"short\":\"Chg. from Yr. Ago\"}," +
                        "\"pch\":{\"full\":\"Percent Change\",\"short\":\"% Chg.\"}," +
                        "\"pc1\":{\"full\":\"Percent Change from Year Ago\",\"short\":\"% Chg. from Yr. Ago\"}," +
                        "\"pca\":{\"full\":\"Compounded Annual Rate of Change\",\"short\":\"Comp. Annual Rate of Chg.\"}," +
                        "\"cch\":{\"full\":\"Continuously Compounded Rate of Change\",\"short\":\"Cont. Comp. Rate of Chg.\"}," +
                        "\"cca\":{\"full\":\"Continuously Compounded Annual Rate of Change\",\"short\":\"Cont. Comp. " +
                        "Annual Rate of Chg.\"},\"log\":{\"full\":\"Natural Log\",\"short\":\"Log\"}," +
                        "\"nbd\":{\"full\":\"Index (Scale value to 100 for chosen period)\",\"short\":\"Index\"}}," +
                    "\"line_color\":\"#4572a7\",\"line_style\":\"solid\",\"lw\":2,\"mark_type\":\"none\"," +
                    "\"mw\":3,\"scale\":\"left\",\"decimal_places\":\"2\",\"frequency\":\"Daily\",\"fq\":\"Daily\"," +
                    "\"available_colors\":{\"1\":\"#4572A7\",\"2\":\"#AA4643\",\"3\":\"#89A54E\",\"4\":\"#80699B\"," +
                    "\"5\":\"#3D96AE\",\"6\":\"#DB843D\",\"7\":\"#92A8CD\",\"8\":\"#A47D7C\",\"9\":\"#B5CA92\",\"10\":\"#91e8e1\"," +
                    "\"11\":\"#8d4653\",\"12\":\"#8085e8\"},\"available_fams\":{\"Average\":\"avg\"," +
                    "\"Sum\":\"sum\",\"End of Period\":\"eop\"},\"fam\":\"avg\"," +
                    "\"available_fqs\":[\"Daily\",\"Weekly, Ending Friday\",\"Weekly, Ending Thursday\"," +
                        "\"Weekly, Ending Wednesday\",\"Weekly, Ending Tuesday\",\"Weekly, Ending Monday\"," +
                        "\"Weekly, Ending Sunday\",\"Weekly, Ending Saturday\",\"Biweekly, Ending Wednesday\"," +
                        "\"Biweekly, Ending Monday\",\"Monthly\",\"Quarterly\",\"Semiannual\",\"Annual\"],\"fml\":\"a\"," +
                        "\"fgst\":\"lin\",\"fgsnd\":\"2020-02-01\",\"all_fred_series_have_same_frequency\":true," +
                        "\"has_fred_series_with_nbd_transformation\":false,\"cosd\":\"2017-05-05\",\"coed\":\"2022-05-05\"," +
                    "\"min_date\":\"1976-06-01\",\"max_date\":\"2022-05-05\",\"year_range\":46,\"ost\":-99999," +
                    "\"oet\":99999,\"available_mmas\":[0,1,2],\"mma\":0,\"graph_series_ids\":[\"T10Y2Y\"]," +
                    "\"series_objects\":{\"a\":{\"series_id\":\"T10Y2Y\"," +
                    "\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\"," +
                    "\"season\":\"Not Seasonally Adjusted\",\"season_short\":\"NSA\",\"frequency\":\"Daily\"," +
                    "\"frequency_short\":\"D\",\"units\":\"Percent\",\"units_short\":\"%\",\"keywords\":\"   \"," +
                    "\"notes\":\"Starting with the update on June 21, 2019, the Treasury bond data used in calculating interest rate spreads is obtained directly from the U.S. Treasury Department (https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Pages/TextView.aspx?data=yield).\\r\\nSeries is calculated as the spread between 10-Year Treasury Constant Maturity (BC_10YEAR) and 2-Year Treasury Constant Maturity (BC_2YEAR). Both underlying series are published at the U.S. Treasury Department (https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Pages/TextView.aspx?data=yield).\"," +
                    "\"all_obs_transformations\":{\"lin\":\"Percent\",\"cap\":\"Percent per Capita\",\"chg\":\"Change, Percent\"," +
                    "\"ch1\":\"Change from Year Ago, Percent\",\"pch\":\"Percent Change\",\"pc1\":\"Percent Change from Year Ago\"," +
                    "\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\"," +
                    "\"cca\":\"Continuously Compounded Annual Rate of Change\"}," +
                    "\"abbreviated_all_obs_transformations\":{\"lin\":\"Levels\",\"cap\":\"Levels per Capita\",\"chg\":\"Chg.\"," +
                    "\"ch1\":\"Chg. from Yr. Ago\",\"pch\":\"% Chg.\",\"pc1\":\"% Chg. from Yr. Ago\",\"pca\":\"Comp. Annual Rate of Chg.\"," +
                    "\"cch\":\"Cont. Comp. Rate of Chg.\",\"cca\":\"Cont. Comp. Annual Rate of Chg.\",\"log\":\"Log\"}," +
                    "\"obs_transformations\":{\"lin\":\"Percent\",\"chg\":\"Change, Percent\",\"ch1\":\"Change from Year Ago, " +
                    "Percent\",\"pch\":\"Percent Change\",\"pc1\":\"Percent Change from Year Ago\"," +
                    "\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\"," +
                    "\"cca\":\"Continuously Compounded Annual Rate of Change\"},\"valid_start_date\":\"2022-05-06\"," +
                    "\"valid_end_date\":\"2022-05-06\",\"vintage_date\":\"2022-05-06\",\"available_revision_dates\":" +
                    "[\"2022-05-06\"],\"revision_date\":\"2022-05-06\",\"relative_vintage\":null,\"nd\":\"1976-06-01\"," +
                    "\"step_line\":\"f\",\"transformation\":\"lin\",\"available_units\":{\"lin\":\"Percent\",\"chg\":\"Change, " +
                    "Percent\",\"ch1\":\"Change from Year Ago, Percent\",\"pch\":\"Percent Change\"," +
                    "\"pc1\":\"Percent Change from Year Ago\",\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\"," +
                    "\"cca\":\"Continuously Compounded Annual Rate of Change\"},\"min_valid_start_date\":\"2022-05-05\"," +
                    "\"max_valid_start_date\":null,\"min_obs_start_date\":\"1976-06-01\",\"max_obs_start_date\":\"2022-05-05\"," +
                    "\"last_updated\":\"2022-05-05 16:01:02-05\"}},\"lsv\":null,\"lev\":null,\"observation_grouping_approximation\":\"close\"," +
                    "\"chart_key\":\"f13da844cc512c544a7636a7722ada84\"}],\"fredLogo\":{\"width\":105,\"height\":22," +
                    "\"image\":\"https://fred.stlouisfed.org/images/fredgraph-logo-2x.png\"}," +
                    "\"container\":{\"width\":1168,\"height\":450},\"defaultFontSize\":12,\"defaultMaxTicks\":10," +
                    "\"showNavigator\":true,\"showCredits\":true,\"dataParams\":{},\"dataUrl\":\"https://fred-sa.stlouisfed.org/graph/graph-data.php\"," +
                    "\"graphBreakpointWidth\":400,\"observations\":{},\"axisTitleHeight\":300," +
                    "\"titleWidth\":1003,\"alignTicks\":true,\"borderRadius\":0,\"recessionBars\":\"on\",\"spacingBottom\":15," +
                    "\"spacingTop\":50,\"spacingRight\":20,\"zoomType\":\"x\",\"style\":{\"fontFamily\":\"Open Sans\"," +
                    "\"color\":\"color: rgb(68, 68, 68)\"},\"chartTypes\":{\"line\":\"LINE\",\"area\":\"AREA\",\"column\":\"BAR\"," +
                    "\"scatter\":\"PLOT\",\"pie\":\"SEGMENT\"},\"chartTypeIcons\":{\"line\":\"fa-chart-line\",\"area\":\"fa-chart-area\"," +
                    "\"column\":\"fa-chart-bar\",\"pie\":\"fa-chart-pie\",\"scatter\":\"\"}}";
        // JSONObject tempJs = JSON.parseObject(paraStr);
        Map<String ,String> para = JSONObject.parseObject(paraStr, new TypeReference<Map<String, String>>(){});
        // // Map<String ,String> para = JSON.parseObject(tempJs, new TypeReference<Map<String, String>>(){});
        //
        //
        Connection connection = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true)
                .header("Content-Type", "application/json; charset=UTF-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");

        connection.requestBody(paraStr);

        //
        //
        // //请求头设置，特别是cookie设置
        // // connection.header("Accept", "text/html, application/xhtml+xml, */*");
        // // connection.header("Content-Type", "application/x-www-form-urlencoded");
        // // connection.postDataCharset("GBK");
        // connection.header("Content-Type", "application/json;charset=utf-8");
        // // connection.data(paraStr);
        //
        // Connection.Response response = connection.execute();
        // Document document = Jsoup.parse(response.body());
        Document document = connection.post();
        System.out.println(document);

        // String paraStr = "{\"hostName\":\"fred.stlouisfed.org\",\"series\":[],\"chart\":{\"labels\":{\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\",\"subtitle\":\"Source: Federal Reserve Bank of St. Louis\",\"left_axis\":\"Percent\",\"right_axis\":\"\",\"bottom_axis\":\"\",\"bubble-size_axis\":\"\",\"footer\":\"2022 fred.stlouisfed.org\"},\"cosd\":\"2017-05-06\",\"coed\":\"2022-05-06\",\"min_date\":\"1976-06-01\",\"max_date\":\"2022-05-06\",\"frequency\":\"Daily\",\"width\":1168,\"height\":450,\"drp\":0,\"stacking\":null,\"txtcolor\":\"#444444\",\"mode\":\"fred\",\"ts\":12,\"tts\":12,\"fo\":\"open sans\",\"x_scale\":\"time\",\"trc\":0,\"nt\":0,\"thu\":0,\"bgcolor\":\"#E1E9F0\",\"graph_bgcolor\":\"#ffffff\",\"showLegend\":\"yes\",\"showAxisTitles\":\"yes\",\"zoomType\":\"x\",\"showTooltip\":\"yes\",\"chartType\":\"line\",\"recession_bars\":\"on\",\"showNavigator\":\"true\",\"available_chart_types\":[\"line\",\"area\",\"column\",\"scatter\",\"pie\",\"bubble\"],\"log_scales\":{\"left\":false,\"right\":false,\"bottom\":false,\"bubble-size\":false},\"available_stacking\":[\"normal\",\"percent\"],\"legacy_url\":\"bgcolor=%23E1E9F0&chart_type=line&drp=0&fo=open%20sans&graph_bgcolor=%23ffffff&height=450&mode=fred&recession_bars=on&txtcolor=%23444444&ts=12&tts=12&width=1168&nt=0&thu=0&trc=0&show_legend=yes&show_axis_titles=yes&show_tooltip=yes&id=T10Y2Y&scale=left&cosd=2017-05-06&coed=2022-05-06&line_color=%234572a7&link_values=false&line_style=solid&mark_type=none&mw=3&lw=2&ost=-99999&oet=99999&mma=0&fml=a&fq=Daily&fam=avg&fgst=lin&fgsnd=2020-02-01&line_index=0&transformation=lin&vintage_date=2022-05-07&revision_date=2022-05-07&nd=1976-06-01\",\"piedate\":null,\"lastModified\":\"Fri, 06 May 2022 21:01:02 GMT\",\"obsFetch\":false,\"initialPayload\":true},\"xAxis\":{},\"yAxis\":{},\"seriesObjects\":[{\"type\":\"time-series\",\"line_index\":1,\"legendIndex\":1,\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\",\"hide_marks\":true,\"available_formula_transformations\":{\"lin\":{\"full\":\"\",\"short\":\"\"},\"chg\":{\"full\":\"Change\",\"short\":\"Chg.\"},\"ch1\":{\"full\":\"Change from Year Ago\",\"short\":\"Chg. from Yr. Ago\"},\"pch\":{\"full\":\"Percent Change\",\"short\":\"% Chg.\"},\"pc1\":{\"full\":\"Percent Change from Year Ago\",\"short\":\"% Chg. from Yr. Ago\"},\"pca\":{\"full\":\"Compounded Annual Rate of Change\",\"short\":\"Comp. Annual Rate of Chg.\"},\"cch\":{\"full\":\"Continuously Compounded Rate of Change\",\"short\":\"Cont. Comp. Rate of Chg.\"},\"cca\":{\"full\":\"Continuously Compounded Annual Rate of Change\",\"short\":\"Cont. Comp. Annual Rate of Chg.\"},\"log\":{\"full\":\"Natural Log\",\"short\":\"Log\"},\"nbd\":{\"full\":\"Index (Scale value to 100 for chosen period)\",\"short\":\"Index\"}},\"line_color\":\"#4572a7\",\"line_style\":\"solid\",\"lw\":2,\"mark_type\":\"none\",\"mw\":3,\"scale\":\"left\",\"decimal_places\":\"2\",\"frequency\":\"Daily\",\"fq\":\"Daily\",\"available_colors\":{\"1\":\"#4572A7\",\"2\":\"#AA4643\",\"3\":\"#89A54E\",\"4\":\"#80699B\",\"5\":\"#3D96AE\",\"6\":\"#DB843D\",\"7\":\"#92A8CD\",\"8\":\"#A47D7C\",\"9\":\"#B5CA92\",\"10\":\"#91e8e1\",\"11\":\"#8d4653\",\"12\":\"#8085e8\"},\"available_fams\":{\"Average\":\"avg\",\"Sum\":\"sum\",\"End of Period\":\"eop\"},\"fam\":\"avg\",\"available_fqs\":[\"Daily\",\"Weekly, Ending Friday\",\"Weekly, Ending Thursday\",\"Weekly, Ending Wednesday\",\"Weekly, Ending Tuesday\",\"Weekly, Ending Monday\",\"Weekly, Ending Sunday\",\"Weekly, Ending Saturday\",\"Biweekly, Ending Wednesday\",\"Biweekly, Ending Monday\",\"Monthly\",\"Quarterly\",\"Semiannual\",\"Annual\"],\"fml\":\"a\",\"fgst\":\"lin\",\"fgsnd\":\"2020-02-01\",\"all_fred_series_have_same_frequency\":true,\"has_fred_series_with_nbd_transformation\":false,\"cosd\":\"2017-05-06\",\"coed\":\"2022-05-06\",\"min_date\":\"1976-06-01\",\"max_date\":\"2022-05-06\",\"year_range\":46,\"ost\":-99999,\"oet\":99999,\"available_mmas\":[0,1,2],\"mma\":0,\"graph_series_ids\":[\"T10Y2Y\"],\"series_objects\":{\"a\":{\"series_id\":\"T10Y2Y\",\"title\":\"10-Year Treasury Constant Maturity Minus 2-Year Treasury Constant Maturity\",\"season\":\"Not Seasonally Adjusted\",\"season_short\":\"NSA\",\"frequency\":\"Daily\",\"frequency_short\":\"D\",\"units\":\"Percent\",\"units_short\":\"%\",\"keywords\":\"   \",\"notes\":\"Starting with the update on June 21, 2019, the Treasury bond data used in calculating interest rate spreads is obtained directly from the U.S. Treasury Department (https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Pages/TextView.aspx?data=yield).\\r\\nSeries is calculated as the spread between 10-Year Treasury Constant Maturity (BC_10YEAR) and 2-Year Treasury Constant Maturity (BC_2YEAR). Both underlying series are published at the U.S. Treasury Department (https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Pages/TextView.aspx?data=yield).\",\"all_obs_transformations\":{\"lin\":\"Percent\",\"cap\":\"Percent per Capita\",\"chg\":\"Change, Percent\",\"ch1\":\"Change from Year Ago, Percent\",\"pch\":\"Percent Change\",\"pc1\":\"Percent Change from Year Ago\",\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\",\"cca\":\"Continuously Compounded Annual Rate of Change\"},\"abbreviated_all_obs_transformations\":{\"lin\":\"Levels\",\"cap\":\"Levels per Capita\",\"chg\":\"Chg.\",\"ch1\":\"Chg. from Yr. Ago\",\"pch\":\"% Chg.\",\"pc1\":\"% Chg. from Yr. Ago\",\"pca\":\"Comp. Annual Rate of Chg.\",\"cch\":\"Cont. Comp. Rate of Chg.\",\"cca\":\"Cont. Comp. Annual Rate of Chg.\",\"log\":\"Log\"},\"obs_transformations\":{\"lin\":\"Percent\",\"chg\":\"Change, Percent\",\"ch1\":\"Change from Year Ago, Percent\",\"pch\":\"Percent Change\",\"pc1\":\"Percent Change from Year Ago\",\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\",\"cca\":\"Continuously Compounded Annual Rate of Change\"},\"valid_start_date\":\"2022-05-07\",\"valid_end_date\":\"2022-05-07\",\"vintage_date\":\"2022-05-07\",\"available_revision_dates\":[\"2022-05-07\"],\"revision_date\":\"2022-05-07\",\"relative_vintage\":null,\"nd\":\"1976-06-01\",\"step_line\":\"f\",\"transformation\":\"lin\",\"available_units\":{\"lin\":\"Percent\",\"chg\":\"Change, Percent\",\"ch1\":\"Change from Year Ago, Percent\",\"pch\":\"Percent Change\",\"pc1\":\"Percent Change from Year Ago\",\"pca\":\"Compounded Annual Rate of Change\",\"cch\":\"Continuously Compounded Rate of Change\",\"cca\":\"Continuously Compounded Annual Rate of Change\"},\"min_valid_start_date\":\"2022-05-06\",\"max_valid_start_date\":null,\"min_obs_start_date\":\"1976-06-01\",\"max_obs_start_date\":\"2022-05-06\",\"last_updated\":\"2022-05-06 16:01:02-05\"}},\"lsv\":null,\"lev\":null,\"observation_grouping_approximation\":\"close\",\"chart_key\":\"f13da844cc512c544a7636a7722ada84\"}],\"fredLogo\":{\"width\":105,\"height\":22,\"image\":\"https://fred.stlouisfed.org/images/fredgraph-logo-2x.png\"},\"container\":{\"width\":1168,\"height\":450},\"defaultFontSize\":12,\"defaultMaxTicks\":10,\"showNavigator\":true,\"showCredits\":true,\"dataParams\":{},\"dataUrl\":\"https://fred-sa.stlouisfed.org/graph/graph-data.php\",\"graphBreakpointWidth\":400,\"observations\":{},\"axisTitleHeight\":300,\"titleWidth\":1003,\"alignTicks\":true,\"borderRadius\":0,\"recessionBars\":\"on\",\"spacingBottom\":15,\"spacingTop\":50,\"spacingRight\":20,\"zoomType\":\"x\",\"style\":{\"fontFamily\":\"Open Sans\",\"color\":\"color: rgb(68, 68, 68)\"},\"chartTypes\":{\"line\":\"LINE\",\"area\":\"AREA\",\"column\":\"BAR\",\"scatter\":\"PLOT\",\"pie\":\"SEGMENT\"},\"chartTypeIcons\":{\"line\":\"fa-chart-line\",\"area\":\"fa-chart-area\",\"column\":\"fa-chart-bar\",\"pie\":\"fa-chart-pie\",\"scatter\":\"\"}}";
        // Map<String ,String> para = JSONObject.parseObject(paraStr, new TypeReference<Map<String, String>>(){});
        // paraStr.replaceAll("\r","").replaceAll("\n", "");
        // PostMethod postMethod = new PostMethod(url);
        // postMethod.setRequestHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        // // NameValuePair[] nameValuePairs = { new NameValuePair("params", paraStr)};
        // NameValuePair[] nameValuePairs = {
        //         new NameValuePair("hostName", "fred.stlouisfed.org"),
        //         new NameValuePair("series","[]")
        // };
        // postMethod.setRequestBody(nameValuePairs);
        // HttpClient httpClient = new HttpClient();
        // int responseCode = httpClient.executeMethod(postMethod);
        // System.out.println("响应码：" + responseCode);
        // String responseBodyAsString = postMethod.getResponseBodyAsString();
        // System.out.println(responseBodyAsString);




    }

    @GetMapping("/getDateTime")
    public RequestResult getDateTime(@RequestParam("dateTime")Long dateTime) {
        Date date = new Date(dateTime);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = sdf1.format(date);
        return new RequestResult(ResponseCode.OK, strDate1, "success");
    }

    private boolean doWork(){
        Constant.IS_WORK= true;
        Thread workThread =new Thread(new Runnable() {
            @Override
            public void run() {
                while (workQueue.size() > 0) {
                    String poll = workQueue.poll();
                    System.out.println(Thread.currentThread().getName() + ":"+poll);
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Constant.IS_WORK= false;
            }
        });
        workThread.start();
        return true;
    }

    @GetMapping("/a")
    public boolean testA(@RequestParam("str")String str) {
        // Date date = new Date(dateTime);
        // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // String strDate1 = sdf1.format(date);

        // JSONObject resJs = new JSONObject();
        // resJs.put("res", "test");
        // return resJs;
        workQueue.add(str);
        if(!Constant.IS_WORK){
            doWork();
        }else {
            log.info("直接入队列");
        }
        return true;
    }

    @GetMapping("/getAllComment")
    public RequestResult getAllComment() {
        List<Comment> commentList = commentService.findCommentList();
        return new RequestResult(ResponseCode.OK, commentList, "success");
    }



}
