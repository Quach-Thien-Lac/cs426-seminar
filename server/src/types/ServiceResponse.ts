interface ServiceResponsePayload {
	message: String,
	data: any
}

interface ServiceResponse {
	success: Boolean,
	statusCode: Number,
	payload: ServiceResponsePayload
}

export default ServiceResponse;