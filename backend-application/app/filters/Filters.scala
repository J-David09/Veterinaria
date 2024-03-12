package filters
import play.api.http.DefaultHttpFilters
import javax.inject.Inject
import play.filters.cors.CORSFilter

class Filters @Inject() (corsFilter: CORSFilter) extends DefaultHttpFilters(corsFilter)