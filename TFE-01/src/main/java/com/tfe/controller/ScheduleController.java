package com.tfe.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfe.model.SegmentHoraire;
import com.tfe.repository.ISegmentHoraireRepository;

@Controller
@RequestMapping("/classe/{code}/schedule")
public class ScheduleController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ISegmentHoraireRepository segmentHoraireRepository;

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String scheduleGet(@PathVariable String code, Model model, SegmentHoraire segment) {
		log.info("methode schedule GET");
		log.info("code: " + code);
		
		model.addAttribute("codeClasse", code);
		model.addAttribute("segmentHoraire", segment);
		//recuperation des segments horaires du lundi
		List<SegmentHoraire> segmentsHoraireLundi = segmentHoraireRepository.getSegmentsHorairesJourClasse(1, code);
		//model.addAttribute("lundi", segmentsHoraireLundi );
		//recuperation des segments horaires du mardi
		List<SegmentHoraire> segmentsHoraireMardi = segmentHoraireRepository.getSegmentsHorairesJourClasse(2, code);
		//model.addAttribute("mardi", segmentsHoraireMardi );
		//recuperation des segments horaires du mercredi
		List<SegmentHoraire> segmentsHoraireMercredi = segmentHoraireRepository.getSegmentsHorairesJourClasse(3, code);
		List<SegmentHoraire> segmentsHoraireJeudi = segmentHoraireRepository.getSegmentsHorairesJourClasse(4, code);
		List<SegmentHoraire> segmentsHoraireVendredi = segmentHoraireRepository.getSegmentsHorairesJourClasse(5, code);
		
		//model.addAttribute("mercredi", segmentsHoraireMercredi );
		
		Map<String,List<SegmentHoraire>> map = new LinkedHashMap<String, List<SegmentHoraire>>();
		map.put("lundi",segmentsHoraireLundi);
		map.put("mardi",segmentsHoraireMardi);
		map.put("mercredi",segmentsHoraireMercredi);
		map.put("jeudi",segmentsHoraireJeudi);
		map.put("vendredi",segmentsHoraireVendredi);
		
		model.addAttribute("horaire", map);
		log.info(map.get("mardi").toString());
		
		return "schedule/schedule";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String schedulePost(SegmentHoraire segment, Model model) {
		log.info("methode schedule POST");
		log.info(segment.getMatiere());
		log.info(segment.getHeureDebut().toString());
		log.info(segment.getHeureFin().toString());
		log.info(segment.getJour().toString());
		
		segmentHoraireRepository.save(segment);
		
		
		return "redirect:/classe/{code}/schedule/add";
		
	}

}
