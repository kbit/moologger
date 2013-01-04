package org.moologger.core.parser.registry.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.moologger.core.parser.Parser;
import org.moologger.core.parser.registry.ParserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserRegistryImpl implements ParserRegistry {
	
	private Set<String> clients = new HashSet<String>();
	private Set<String> protocols = new HashSet<String>();
	private Map<ParserKey, Parser> parsers = new HashMap<ParserKey, Parser>();
	
	@Autowired
	public ParserRegistryImpl(List<Parser> parsers) {
		for (Parser parser : parsers) {
			String client = parser.getClient();
			String protocol = parser.getProtocol();
			ParserKey key = new ParserKey(parser.getClient(), parser.getProtocol());
			
			this.clients.add(client);
			this.protocols.add(protocol);
			if (!this.parsers.containsKey(key)) {
				this.parsers.put(key, parser);
			}
		}
	}
	
	public Set<String> getClients() {
		return Collections.unmodifiableSet(clients);
	}
	
	public Set<String> getProtocols() {
		return Collections.unmodifiableSet(protocols);
	}
	
	public Parser getParser(String client, String protocol) {
		return parsers.get(new ParserKey(client, protocol));
	}
	
	private class ParserKey {
		
		private String client;
		private String protocol;
		
		private ParserKey(String client, String protocol) {
			this.client = client;
			this.protocol = protocol;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) { 
				return false; 
			}
			if (obj == this) { 
				return true; 
			}
			if (obj.getClass() != getClass()) {
			     return false;
			}
			ParserKey key = (ParserKey) obj;
			return new EqualsBuilder()
					.append(client, key.client)
					.append(protocol, key.protocol)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder(17, 37)
		    	.append(client)
		    	.append(protocol)
		    	.toHashCode();
		}
		
	}

}