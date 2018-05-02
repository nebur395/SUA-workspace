package es.upv.dsic.mitss.sua.smartcarsae.mapek.impl;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IEvent;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IKnowledge;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IMonitor;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IPlannifier;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.ISystemConfiguration;
import es.upv.pros.tatami.autonomic.adaptation.framework.systemAPI.componentConfigurator.interfaces.IAdaptiveReadyComponentConfigurator;
import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.IAnalyzer;

import java.util.List;

import es.upv.dsic.mitss.sua.smartcarsae.mapek.interfaces.EMonitorRT;

public class Monitor implements IMonitor {
        
        Analyzer analyzer;
        IKnowledge knowledge;

        @Override
        public void notifyEvent(IEvent event) {
                // TODO Auto-generated method stub
                if(event.getRT().equals(EMonitorRT.CollisionSensorFailure)) {
                        this.getAnalyzer().notifyEvent(event);
                }
                else if(event.getRT().equals(EMonitorRT.ApproachingCity)) {
                        ISystemConfiguration system_configuration = this.getKnowledge().getCurrentSystemConfiguration();
                        List<IAdaptiveReadyComponentConfigurator> list_components = system_configuration.getAdaptiveReadyComponentList();
                        this.getAnalyzer().notifyEvent(event);
                }
                
                
        }
        
        public void setAnalyzer(Analyzer analyzer) {
                this.analyzer = analyzer;
        }
        
        public Analyzer getAnalyzer() {
                return this.analyzer;
        }

        public IKnowledge getKnowledge() {
                return knowledge;
        }
        
        


}

