from spyne.decorator import rpc
from spyne.service import ServiceBase
from spyne.model.primitive import String, Double
from spyne.model.complex import Iterable
from ecb_data import fetch_ecb_rates, convert


class CurrencyConverterService(ServiceBase):
    @rpc(String, String, Double, _returns=Iterable(String))
    def convert_currency(ctx, from_currency, to_currency, amount):
        rates = fetch_ecb_rates()  # Fetch rates (TODO: consider caching this)
        converted_amount = convert(from_currency, to_currency, amount, rates)
        yield f"Converted {amount} {from_currency} to {converted_amount} {to_currency}"
