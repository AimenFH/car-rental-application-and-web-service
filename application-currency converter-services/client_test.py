from zeep import Client
from zeep.exceptions import Fault, TransportError, XMLSyntaxError


WSDL_URL_LOCAL = 'http://127.0.0.1:8000/?wsdl'
WSDL_URL = 'https://soap-currency-converter.azurewebsites.net/?wsdl'

def convert_currency(from_currency, to_currency, amount):
    try:
        client = Client(wsdl=WSDL_URL)
        service = client.service
        response = service.convert_currency(from_currency, to_currency, amount)
        return response
    except (Fault, TransportError, XMLSyntaxError) as e:
        print(f"Error: {e}")
        return None


if __name__ == '__main__':
    from_currency = 'USD'
    to_currency = 'EUR'
    amount = 100

    # Make the conversion request
    print(f"Converting {amount} {from_currency} to {to_currency}...")
    result = convert_currency(from_currency, to_currency, amount)

    if result is not None:
        print(f"Response: {result}")
    else:
        print("Failed to get a response from the service.")
