package shared.models;

import shared.definitions.ResourceType;

/**
 * Harbors allow players to trade resources more favorably. An instance of the Harbor
 * class represents which resource and at which ratio that resource may be traded with
 * at the bank.
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class Harbor {
	private ResourceType resource;
	private int ratio;
}
