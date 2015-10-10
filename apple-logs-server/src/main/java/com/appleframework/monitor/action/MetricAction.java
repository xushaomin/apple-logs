/**
 * Copyright (C) 2012 skymobi LTD
 *
 * Licensed under GNU GENERAL PUBLIC LICENSE  Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appleframework.monitor.action;

import java.util.List;

import javax.annotation.Resource;

import com.appleframework.monitor.model.ChartView;
import com.appleframework.monitor.util.ChartUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.appleframework.monitor.model.MetricValue;
import com.appleframework.monitor.model.Project;
import com.appleframework.monitor.model.TimeRange;
import com.appleframework.monitor.service.ProjectService;

/**
 * @author  Hill.Hu,steven.zheng
 */
@Controller
public class MetricAction {
	
    @Resource
    private ProjectService projectService;

	@RequestMapping(value = "/projects/{projectName}/metrics", method = RequestMethod.GET)
    public @ResponseBody ModelMap  renderVar(ModelMap map, @PathVariable String projectName, String metricName,String newChartTitle) {
    	Project project = projectService.findProject(projectName);
    	if(StringUtils.isNotEmpty(newChartTitle)){
	    		project.getViews().put(newChartTitle, metricName);
	    		projectService.saveProject(project);
    	}
        String[] metrics = metricName.split(",");
        List<List<MetricValue>>  metricLists=Lists.newArrayList();
        for(String name:metrics){
            metricLists.add(project.findMetricData(name));
        }
        map.put("data", ChartUtil.format(metricLists));
        return  map;
    }

    @RequestMapping(value = "/projects/{projectName}/metrics/add", method = RequestMethod.POST)
    public @ResponseBody
    ChartView addMetricView(ModelMap map, @PathVariable String projectName,HttpEntity<ChartView> entity ) {
        ChartView chartView=entity.getBody();
        Assert.notNull(chartView.getTitle());
        Project project = projectService.findProject(projectName);
        project.getChartViews().add(chartView);
        projectService.saveProject(project);
        return  chartView;
    }
    
    @RequestMapping(value = "/projects/{projectName}/metrics/show", method = RequestMethod.GET)
    public @ResponseBody ModelMap  show(ModelMap map, @PathVariable String projectName, String title) {
        Project project = projectService.findProject(projectName);
        List<List<MetricValue>>  metricLists=Lists.newArrayList();
            ChartView view = null;
        for(ChartView chartView:project.getChartViews()){
            if(title.equals(chartView.getTitle())) {
                view=chartView;
                break;
            }
        }
        if(view!=null){
            for(String name:view.getMetricNames()){
                metricLists.add(project.findMetricData(name));
            }
            map.put("data", ChartUtil.format(metricLists,false));
        }
        return  map;
    }
    
    @RequestMapping(value = "/projects/{projectName}/metrics/destroy")
    public @ResponseBody ModelMap  metricsDelete(ModelMap map, @PathVariable String projectName, String title) {
    	Project project = projectService.findProject(projectName);
    	if(StringUtils.isNotEmpty(title)){
            for(int i=0;i<project.getChartViews().size();i++)
    		if(project.getChartViews().get(i).getTitle().equals(title)){
	    		project.getChartViews().remove(i);
	    		projectService.saveProject(project);
    		}
    	}
    	return  map;
    }



    @RequestMapping(value = "/projects/{projectName}/setting/timeRange", method = RequestMethod.POST)
    public @ResponseBody String timeRange(@PathVariable String projectName, HttpEntity<TimeRange> entity) {
        TimeRange timeRange=entity.getBody();
        Assert.notNull(timeRange,"time rage should not be null");
        Project project = projectService.findProject(projectName);
        project.setTimeRange(timeRange);
        projectService.saveProject(project);
        return "true";
    }
}
