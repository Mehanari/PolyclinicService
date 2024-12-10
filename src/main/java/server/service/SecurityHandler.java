package server.service;

import jakarta.xml.bind.*;
import jakarta.xml.soap.*;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import jakarta.xml.ws.soap.SOAPFaultException;

import javax.xml.namespace.QName;
import java.util.Objects;
import java.util.Set;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
    public static final String CLIENT_SECURITY_TOKEN_NAME = "clientToken";
    public static final String SERVER_SECURITY_TOKEN_NAME = "serverToken";
    private final String TOKEN_BASE = "token_";
    private final JAXBContext jaxb;
    private int tokenCounter = 1;

    public SecurityHandler() throws JAXBException {
        jaxb = JAXBContext.newInstance("entity");
    }

    @Override
    public Set<QName> getHeaders() {
        return Set.of();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        boolean outbound = (boolean) soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        boolean result = true;
        if (outbound) {
            System.out.println("Outbound message.");
            result = addServerToken(soapMessageContext.getMessage());
        } else {
            System.out.println("Inbound message.");
            result =  validateUserToken(soapMessageContext.getMessage());
        }
        return result;
    }

    private boolean addServerToken(SOAPMessage message) {
        try {
            SOAPHeader header = message.getSOAPHeader();
            header.extractAllHeaderElements();
            Marshaller marshaller = jaxb.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            QName serverTokenQName = new QName(Const.SERVICE_NS, SERVER_SECURITY_TOKEN_NAME);
            tokenCounter++;
            JAXBElement<String> tokenElement = new JAXBElement<>(serverTokenQName, String.class, TOKEN_BASE + tokenCounter);
            marshaller.marshal(tokenElement, header);
            message.saveChanges();
            System.out.println("Server token: " + TOKEN_BASE + tokenCounter);
        } catch (SOAPException | JAXBException e) {
            String msg = e.getCause() != null ? e.getMessage() + "\nCause: " + e.getCause().getMessage()
                    : e.getMessage();
            System.err.println(msg);
        }
        return true;
    }

    private boolean validateUserToken(SOAPMessage message) {
        String token = "";
        try {
            QName clientTokenQName = new QName(Const.SERVICE_NS, CLIENT_SECURITY_TOKEN_NAME);
            SOAPHeader header = message.getSOAPPart().getEnvelope().getHeader();
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            Node tokenElement = header.getChildElements(clientTokenQName).next();
            token = unmarshaller.unmarshal(tokenElement, String.class).getValue();
            if (Objects.equals(token, TOKEN_BASE + 1)){ //Checking reset sequence
                tokenCounter = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!token.equals(TOKEN_BASE + tokenCounter)) {
            SOAPFault fault = createFault(message, "Invalid token");
            throw new SOAPFaultException(fault);
        }
        return true;
    }

    private SOAPFault createFault(SOAPMessage message, String faultString) {
        SOAPFault fault = null;
        try {
            SOAPEnvelope env = message.getSOAPPart().getEnvelope();
            QName faultCode = null;
            String soapProtocol = SOAPConstants.SOAP_1_1_PROTOCOL;
            String code = "Client";
            String prefix = env.lookupPrefix(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE);
            if (prefix == null) {
                soapProtocol = SOAPConstants.SOAP_1_2_PROTOCOL;
                code = "Sender";
                prefix = env.lookupPrefix(SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE);
            }
            faultCode = env.createQName(code, prefix);
            fault = SOAPFactory.newInstance(soapProtocol).createFault(faultString, faultCode);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        return fault;
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        System.out.println("Fault occurred.");
        return false;
    }

    @Override
    public void close(MessageContext messageContext) {
        System.out.println("Handler closed.");
    }
}
