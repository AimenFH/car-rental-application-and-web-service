import requests
import xml.etree.ElementTree as ET


def fetch_ecb_rates():
    url = 'http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml'
    response = requests.get(url)
    xml_root = ET.fromstring(response.content)

    namespaces = {'gesmes': 'http://www.gesmes.org/xml/2002-08-01',
                  'eurofxref': 'http://www.ecb.int/vocabulary/2002-08-01/eurofxref'}
    cube = xml_root.find('.//eurofxref:Cube/eurofxref:Cube', namespaces)
    rates = {child.get('currency'): float(child.get('rate')) for child in cube}

    return rates


def convert(from_currency, to_currency, amount, rates):
    if from_currency == 'EUR':
        amount_in_eur = amount
    else:
        amount_in_eur = amount / rates[from_currency]

    if to_currency == 'EUR':
        return amount_in_eur
    else:
        return amount_in_eur * rates[to_currency]
